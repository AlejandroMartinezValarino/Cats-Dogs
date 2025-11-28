package com.pets.dogs.domain.exception;

public class DogApiException extends RuntimeException{
    public DogApiException(String message) {
        super(message);
    }

    public DogApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public DogApiException(Throwable cause) {
        super(cause);
    }
}
