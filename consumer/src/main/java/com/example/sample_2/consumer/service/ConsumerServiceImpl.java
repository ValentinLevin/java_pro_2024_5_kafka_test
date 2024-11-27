package com.example.sample_2.consumer.service;

import com.example.sample_2.consumer.config.KafkaIntegerConsumerConfig;
import com.example.sample_2.consumer.config.KafkaStringConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.retrytopic.RetryTopicHeaders;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class ConsumerServiceImpl implements ConsumerService {
    private static final String STRING_TOPIC_NAME = "testproducer.string";
    private static final String INTEGER_TOPIC_NAME = "testproducer.integer";

/*
    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 1000, maxDelay = 10_000, multiplier = 2)
    )
*/
    @KafkaListener(
            groupId = KafkaStringConsumerConfig.GROUP_ID,
            containerFactory = "stringKafkaListenerContainerFactory",
            topics = STRING_TOPIC_NAME
    )
    public void stringConsumer(
            @Payload String message,
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(name = KafkaHeaders.OFFSET, required = false) Long offset,
            @Header(name = RetryTopicHeaders.DEFAULT_HEADER_ATTEMPTS, required = false) Integer attempts,
            @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) Integer receivedPartition,
            @Header(name = KafkaHeaders.PARTITION, required = false) String partition,
            @Header(name = KafkaHeaders.ORIGINAL_PARTITION, required = false) String originalPartition,
            @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic

    ) {
        log.info("--- Received string message: {}, topic {}, partition {}, offset {}, attempt {}", message, topic, receivedPartition, offset, attempts);
        if (key != null && key.equals("fail") && attempts < 5) {
            throw new RuntimeException("failed");
        }
    }

    @KafkaListener(
            groupId = KafkaIntegerConsumerConfig.GROUP_ID,
            containerFactory = "integerKafkaListenerContainerFactory",
            topics = INTEGER_TOPIC_NAME
    )
    public void integerConsumer(
            @Payload Integer message,
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(name = KafkaHeaders.OFFSET, required = false) Long offset,
            @Header(name = RetryTopicHeaders.DEFAULT_HEADER_ATTEMPTS, required = false) Integer attempts,
            @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) Integer receivedPartition,
            @Header(name = KafkaHeaders.PARTITION, required = false) String partition,
            @Header(name = KafkaHeaders.ORIGINAL_PARTITION, required = false) String originalPartition,
            @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic

    ) {
        log.info("--- Received integer message: {}, topic {}, partition {}, offset {}, attempt {}", message, topic, receivedPartition, offset, attempts);
        if (key != null && key.equals("fail") && attempts < 5) {
            throw new RuntimeException("failed");
        }
    }
}
