package com.ieum.common.service;

import com.sun.mail.imap.IMAPFolder;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.MimeBodyPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IMAPService {
    // IMAP 프로토콜을 이용해서 메일을 실시간으로 확인하는 서비스
    
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.ssl.enable", "true");

        try {
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            emailFolder.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    Message[] messages = ev.getMessages();
                    System.out.println("Got " + messages.length + " new messages");
                    for (Message message : messages) {
                        try {

                            // 보낸 사람(핸드폰 번호) 출력
                            Address[] fromAddresses = message.getFrom();
                            String from = fromAddresses != null && fromAddresses.length > 0 ? fromAddresses[0].toString() : "Unknown";
                            String phoneNumber = from.replaceAll(".*<(\\d+)@.*", "$1"); // 정규 표현식을 사용하여 전화번호 추출
                            System.out.println("From: " + phoneNumber);

                            // 메일 내용 출력
                            String contentType = message.getContentType();
                            String messageContent = "";

                            if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                                Object content = message.getContent();
                                if (content != null) {
                                    messageContent = content.toString();
                                }
                            } else if (contentType.contains("multipart")) {
                                Multipart multiPart = (Multipart) message.getContent();
                                for (int partCount = 0; partCount < multiPart.getCount(); partCount++) {
                                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                        // 첨부 파일 처리
                                    } else {
                                        messageContent = part.getContent().toString();
                                    }
                                }
                            }

                            // 'ieum' 단어부터 '========' 전까지의 내용만 출력
                            if (messageContent.contains("ieum")) {
                                int startIndex = messageContent.indexOf("ieum");
                                int endIndex = messageContent.indexOf("========", startIndex);
                                String relevantContent = messageContent.substring(startIndex, endIndex != -1 ? endIndex : messageContent.length());
                                System.out.println("Content: " + relevantContent);
                            }

                        } catch (MessagingException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            // 실시간으로 메일을 체크하기 위해 IDLE 커맨드 사용
            // 이 부분은 별도의 스레드에서 처리해야 합니다.
            new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        ((IMAPFolder) emailFolder).idle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
