package com.example.sample_2.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;

@Configuration
public class KafkaTopicConfig {
    public static final String STRING_TOPIC_NAME = "testproducer.string";
    public static final String INTEGER_TOPIC_NAME = "testproducer.integer";
    public static final String OBJECT_TOPIC_NAME = "testproducer.object";

    private final String bootstrapServers;

    public KafkaTopicConfig(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Collections.singletonMap(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers));
    }

    @Bean
    public NewTopic stringTopic() {
        return new NewTopic(STRING_TOPIC_NAME, 10, (short) 1);
    }

    @Bean
    public NewTopic integerTopic() {
        return new NewTopic(INTEGER_TOPIC_NAME, 5, (short) 1);
    }

    @Bean
    public NewTopic objectTopic() {
        return new NewTopic(OBJECT_TOPIC_NAME, 5, (short) 1);
    }
}
