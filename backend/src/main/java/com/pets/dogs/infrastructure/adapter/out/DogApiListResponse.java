package com.pets.dogs.infrastructure.adapter.out;

import lombok.Data;
import java.util.List;

@Data
public class DogApiListResponse {
    private List<String> messageList;
    private String status;

    public List<String> getMessageList() {
        return messageList;
    }

    public String getStatus() {
        return status;
    }
}
