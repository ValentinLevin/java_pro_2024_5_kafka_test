package com.example.sample_2.producer.controller;

import com.example.sample_2.producer.dto.IntegerMessageDTO;
import com.example.sample_2.producer.dto.ObjectMessageDTO;
import com.example.sample_2.producer.dto.StringMessageDTO;
import com.example.sample_2.producer.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("string")
    public ResponseEntity<?> sendStringMessage(@RequestBody StringMessageDTO message) {
        this.producerService.sendStringMessage(message.getPartition(), message.getKey(), message.getMessage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("integer")
    public ResponseEntity<?> sendIntegerMessage(@RequestBody IntegerMessageDTO message) {
        this.producerService.sendIntegerMessage(message.getPartition(), message.getKey(), message.getMessage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("object")
    public ResponseEntity<?> sendObjectMessage(@RequestBody ObjectMessageDTO message) {
        this.producerService.sendObjectMessage(message.getPartition(), message.getMessage().getId(), message.getMessage());
        return ResponseEntity.ok().build();
    }
}
