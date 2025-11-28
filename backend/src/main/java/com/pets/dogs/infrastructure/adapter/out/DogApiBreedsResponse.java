package com.pets.dogs.infrastructure.adapter.out;

import lombok.Data;
import java.util.Map;
import java.util.List;

@Data
public class DogApiBreedsResponse {
    private Map<String, List<String>> message;
    private String status;

    public Map<String, List<String>> getMessage() {
        return message;
    }


    public String getStatus() {
        return status;
    }
}
