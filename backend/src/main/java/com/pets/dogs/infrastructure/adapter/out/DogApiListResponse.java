package com.pets.dogs.infrastructure.adapter.out;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DogApiListResponse {
    @JsonProperty("message")
    private List<String> message;
    
    @JsonProperty("status")
    private String status;

    public List<String> getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
