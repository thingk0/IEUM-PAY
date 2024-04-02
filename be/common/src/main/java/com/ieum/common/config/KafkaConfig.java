package com.ieum.common.config;

import com.ieum.common.message.FundingCompletedMessage;
import com.ieum.common.message.SseConnectionRequestMessage;
import com.ieum.common.message.TransferReceivedMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, TransferReceivedMessage> transferReceivedMessageTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties()));
    }

    @Bean
    public KafkaTemplate<String, FundingCompletedMessage> fundingCompletedMessageTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties()));
    }

    @Bean
    public KafkaTemplate<String, SseConnectionRequestMessage> sseConnectionRequestMessageTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties()));
    }

    @Bean
    public Map<String, Object> kafkaProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, "1");
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        return configProps;
    }

}
