package com.example.sample_2.producer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectMessageDTO {
    private final Integer partition;
    private final UserDTO message;

    @JsonCreator
    public ObjectMessageDTO(
            @JsonProperty("partition") Integer partition,
            @JsonProperty("message") UserDTO message
    ) {
        this.partition = partition;
        this.message = message;
    }
}
