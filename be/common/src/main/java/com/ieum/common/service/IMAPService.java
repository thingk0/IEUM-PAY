package com.ieum.common.service;

import com.sun.mail.imap.IMAPFolder;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.MimeBodyPart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
                    log.info("Got " + messages.length + " new messages");
                    for (Message message : messages) {
                        try {

                            // 보낸 사람(핸드폰 번호) 출력
                            Address[] fromAddresses = message.getFrom();
                            String from = fromAddresses != null && fromAddresses.length > 0 ? fromAddresses[0].toString() : "Unknown";

                            if (!from.startsWith("010")) {
                                continue; // 이 메시지를 무시하고 다음 메시지로 넘어갑니다.
                            }

                            String phoneNumber = from.replaceAll(".*<(\\d+)@.*", "$1"); // 정규 표현식을 사용하여 전화번호 추출
                            log.info("From: " + phoneNumber);

                            // 메일 내용 출력
                            String contentType = message.getContentType();

                            boolean hasAttachment = false;
                            if (contentType.contains("multipart")) {
                                Multipart multiPart = (Multipart) message.getContent();
                                for (int partCount = 0; partCount < multiPart.getCount(); partCount++) {
                                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                        hasAttachment = true;
                                        break; // 첨부파일이 발견되면 더 이상 다른 부분을 확인하지 않습니다.
                                    }
                                }
                            }

                            if (hasAttachment) {
                                // 첨부 파일이 있으면 에러 로그를 출력합니다.
                                log.error("첨부파일이 들어왔습니다.");
                                continue; // 첨부파일이 있는 경우 다른 로직을 수행하지 않습니다.
                            }

                            String messageContent = "";

                            if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                                Object content = message.getContent();
                                if (content != null) {
                                    messageContent = content.toString();
                                }
                            }

                            // 'ieum' 단어부터 '=' 전까지의 내용만 출력
                            if (messageContent.contains("ieum")) {
                                int startIndex = messageContent.indexOf("ieum");
                                int endIndex = messageContent.indexOf("=", startIndex);
                                String relevantContent = messageContent.substring(startIndex, endIndex != -1 ? endIndex : messageContent.length());
                                log.info("Content: " + relevantContent);
                            }

                        } catch (MessagingException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        ((IMAPFolder) emailFolder).idle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
