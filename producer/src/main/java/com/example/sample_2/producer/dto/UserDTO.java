package com.example.sample_2.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
