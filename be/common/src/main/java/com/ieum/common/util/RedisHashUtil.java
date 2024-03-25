package com.ieum.common.util;

import java.time.Duration;
import java.util.Map;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHashUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Object> hashOps;

    public RedisHashUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOps = redisTemplate.opsForHash();
    }

    /**
     * 주어진 키에 대한 해시를 Redis 에 저장하고, 만료 시간을 설정.
     *
     * @param key          저장할 데이터의 키
     * @param data         저장할 데이터 (Map 형태)
     * @param ttlInSeconds 데이터의 TTL(초 단위)
     */
    public void save(String key, Map<String, Object> data, long ttlInSeconds) {
        hashOps.putAll(key, data);
        redisTemplate.expire(key, Duration.ofSeconds(ttlInSeconds));
    }

    /**
     * 주어진 키에 해당하는 전체 해시를 Redis 에서 삭제.
     *
     * @param key 삭제할 데이터의 키
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 주어진 키로 저장된 해시를 조회.
     *
     * @param key 조회할 데이터의 키
     * @return 해당 키에 저장된 해시(Map 형태), 존재하지 않을 경우 비어 있는 Map 반환
     */
    public Map<String, Object> findByKey(String key) {
        return hashOps.entries(key);
    }

    /**
     * 주어진 키와 필드에 해당하는 값을 조회.
     *
     * @param key   조회할 데이터의 키
     * @param field 조회할 데이터의 필드 이름
     * @return 해당 필드에 저장된 값, 존재하지 않을 경우 null 반환
     */
    public Object findField(String key, String field) {
        return hashOps.get(key, field);
    }

    /**
     * 주어진 키의 해시에서 하나 이상의 필드를 삭제.
     *
     * @param key    수정할 데이터의 키
     * @param fields 삭제할 필드의 이름들
     */
    public void deleteField(String key, String... fields) {
        hashOps.delete(key, (Object[]) fields);
    }

    /**
     * 주어진 키와 필드가 Redis 에 존재하는지 확인.
     *
     * @param key   확인할 데이터의 키
     * @param field 확인할 필드 이름
     * @return 필드 존재 여부 (true: 존재함, false: 존재하지 않음)
     */
    public boolean exists(String key, String field) {
        return hashOps.hasKey(key, field);
    }

    /**
     * 주어진 키의 해시에 있는 특정 필드의 값을 업데이트.
     *
     * @param key   업데이트할 데이터의 키
     * @param field 업데이트할 필드의 이름
     * @param value 새로 저장할 값
     */
    public void updateField(String key, String field, Object value) {
        hashOps.put(key, field, value);
    }
}

