package com.example.sample_2.producer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerMessageDTO {
    private final Integer partition;
    private final String key;
    private final Integer message;

    @JsonCreator
    public IntegerMessageDTO(
            @JsonProperty("partition") Integer partition,
            @JsonProperty("key") String key,
            @JsonProperty(value = "message", defaultValue = "0") Integer message
    ) {
        this.partition = partition;
        this.key = key;
        this.message = message;
    }
}
