package com.ieum.alert.consumer;

import com.ieum.alert.document.FcmToken;
import com.ieum.alert.message.FcmConnectionRequestMessage;
import com.ieum.alert.repository.FcmTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmConnectionRequestConsumer {

    private final FcmTokenRepository fcmTokenRepository;

    @KafkaListener(topics = "${topic.fcmConnect}",
                   groupId = "${consumer.fcmConnect}",
                   containerFactory = "fcmConnectionRequestMessageContainerFactory")
    public void handleFcmConnectionRequest(@Payload FcmConnectionRequestMessage message) {
        Long memberId = message.getMemberId();
        String fcmToken = message.getFcmToken();

        fcmTokenRepository.findFcmTokenByMemberId(memberId)
                          .ifPresentOrElse(
                              existingToken -> updateFcmToken(existingToken, fcmToken),
                              () -> createFcmToken(memberId, fcmToken)
                          );
    }

    private void updateFcmToken(FcmToken existingToken, String newToken) {
        try {
            existingToken.updateToken(newToken);
            fcmTokenRepository.save(existingToken);
        } catch (Exception e) {
            log.error("FCM 토큰 업데이트 실패", e);
        }
    }

    private void createFcmToken(Long memberId, String fcmToken) {
        try {
            FcmToken newToken = FcmToken.builder()
                                        .memberId(memberId)
                                        .token(fcmToken)
                                        .deviceType("pwa")
                                        .build();
            fcmTokenRepository.save(newToken);
        } catch (Exception e) {
            log.error("FCM 토큰 생성 실패", e);
        }
    }
}
