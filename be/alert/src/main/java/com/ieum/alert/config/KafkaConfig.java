package com.ieum.alert.config;

import com.ieum.alert.message.FcmConnectionRequestMessage;
import com.ieum.alert.message.TransferReceivedMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransferReceivedMessage> transferReceivedMessageListenerContainerFactory() {
        return kafkaListenerContainerFactory(TransferReceivedMessage.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FcmConnectionRequestMessage> fcmConnectionRequestMessageContainerFactory() {
        return kafkaListenerContainerFactory(FcmConnectionRequestMessage.class);
    }

    private <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageType) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(messageType);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(initProps(), new StringDeserializer(), deserializer);
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> messageType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(messageType));
        return factory;
    }

    private Map<String, Object> initProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        return props;
    }
}
