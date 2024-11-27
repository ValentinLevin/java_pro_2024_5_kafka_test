package com.example.sample_2.producer.service;

import com.example.sample_2.producer.dto.UserDTO;

public interface ProducerService {
    void sendStringMessage(Integer partition, String key, String message);
    void sendIntegerMessage(Integer partition, String key, Integer message);
    void sendObjectMessage(Integer partition, Integer key, UserDTO message);
}
