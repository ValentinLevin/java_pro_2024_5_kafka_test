package com.example.sample_2.producer.service;

import com.example.sample_2.producer.config.KafkaTopicConfig;
import com.example.sample_2.producer.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, Integer> integerKafkaTemplate;
    private final KafkaTemplate<Integer, UserDTO> objectKafkaTemplate;

    public ProducerServiceImpl(
            KafkaTemplate<String, String> stringKafkaTemplate,
            KafkaTemplate<String, Integer> integerKafkaTemplate,
            KafkaTemplate<Integer, UserDTO> objectKafkaTemplate
    ) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.integerKafkaTemplate = integerKafkaTemplate;
        this.objectKafkaTemplate = objectKafkaTemplate;
    }

    @Override
    public void sendStringMessage(Integer partition, String key, String message) {
        this.stringKafkaTemplate.send(KafkaTopicConfig.STRING_TOPIC_NAME, partition, key, message);
    }

    @Override
    public void sendIntegerMessage(Integer partition, String key, Integer message) {
        this.integerKafkaTemplate.send(KafkaTopicConfig.INTEGER_TOPIC_NAME, partition, key, message);
    }

    @Override
    public void sendObjectMessage(Integer partition, Integer key, UserDTO message) {
        this.objectKafkaTemplate.send(KafkaTopicConfig.OBJECT_TOPIC_NAME, partition, key, message)
                .thenAccept((result) -> {
                    log.info("{}", result);
                });
    }
}
