package com.ieum.common.service;

import com.sun.mail.imap.IMAPFolder;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.MimeBodyPart;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class IMAPService {
    // IMAP 프로토콜을 이용해서 메일을 실시간으로 확인하는 서비스

    private final StringRedisTemplate stringRedisTemplate;

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
                            Address[] fromAddresses = message.getFrom();
                            String from = fromAddresses != null && fromAddresses.length > 0 ? fromAddresses[0].toString() : "Unknown";
                            String phoneNumber = from.replaceAll(".*<(\\d+)@.*", "$1"); // 정규 표현식을 사용하여 전화번호 추출
                            log.info("From: " + phoneNumber);

                            String codeInRedis = stringRedisTemplate.opsForValue().get("mms:" + phoneNumber);

                            if (codeInRedis == null) {
                                continue; // 이 메시지를 무시하고 다음 메시지로 넘어갑니다.
                            }
                            // 메일 내용 출력
                            String contentType = message.getContentType();
                            String messageContent = "";

                            if (contentType.contains("multipart")) {
                                Multipart multiPart = (Multipart) message.getContent();
                                for (int partCount = 0; partCount < multiPart.getCount(); partCount++) {
                                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);

                                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) && part.getContentType().contains("text/plain")) {
                                        // 첨부파일이 있는 경우, 파일 유형이 텍스트인지 확인
                                        String attachmentContent = (String) part.getContent();
                                        messageContent += attachmentContent;  // 첨부파일 내용 추가
                                    } else {
                                        messageContent += part.getContent().toString();  // 이메일 본문 내용 추가
                                    }
                                }
                            } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                                messageContent += message.getContent().toString();
                            }

                            String authCode = "ieum-payAuthenticationCode:";
                            int codeStart = messageContent.indexOf(authCode);
                            if (codeStart != -1) {
                                // 인증 코드 이후의 내용을 추출하고 '%' 기준으로 종료 지점을 찾음
                                String afterCode = messageContent.substring(codeStart + authCode.length());
                                int endIdx = afterCode.indexOf('%'); // '%' 문자 위치를 찾음
                                if (endIdx != -1) {
                                    // '%'까지의 문자열을 인증 코드로 추출
                                    authCode += afterCode.substring(0, endIdx + 1).trim(); // '%' 포함하여 추출
                                    log.info(authCode);
                                }
                            }

                            if (authCode.equals(codeInRedis)) {
                                // 메일 내용과 Redis 데이터가 일치하면 새 키 값을 Redis에 저장
                                stringRedisTemplate.opsForValue().set("mmsAuth:" + phoneNumber, "true");
                                log.info("인증 성공: " + phoneNumber);
                            } else {
                                log.info("인증 실패: " + phoneNumber);
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
