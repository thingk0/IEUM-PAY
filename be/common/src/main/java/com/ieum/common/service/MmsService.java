package com.ieum.common.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MmsService {

    private final StringRedisTemplate stringRedisTemplate;
    public String getAuthenticationCode(String phoneNumber) {
        // 영어 대소문자와 숫자를 포함한 16자리 난수 생성
        String code = "ieum-payAuthenticationCode:" + RandomStringUtils.randomAlphanumeric(32) + "%";

        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set("mms:"+phoneNumber, code, 3, TimeUnit.MINUTES);
        // redis 저장 key mms:phoneNumber, value code - 3분 제한

        return code;
    }
}
