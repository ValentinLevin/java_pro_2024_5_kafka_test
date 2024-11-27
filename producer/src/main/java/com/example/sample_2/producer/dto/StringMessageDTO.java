package com.example.sample_2.producer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringMessageDTO {
    private final Integer partition;
    private final String key;
    private final String message;

    @JsonCreator
    public StringMessageDTO(
            @JsonProperty(value = "partition", defaultValue = "0") int partition,
            @JsonProperty(value = "key", defaultValue = "success") String key,
            @JsonProperty("message") String message
    ) {
        this.partition = partition;
        this.key = key;
        this.message = message;
    }
}
