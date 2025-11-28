package com.pets.dogs.infrastructure.adapter.out;

import lombok.Data;

@Data
public class DogApiResponse {
    private Object message;
    private String status;

    public String getMessage() {
        return message instanceof String ? (String) message : null;
    }

}
