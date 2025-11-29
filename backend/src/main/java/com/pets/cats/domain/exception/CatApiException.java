package com.pets.cats.domain.exception;

public class CatApiException extends RuntimeException{

    public CatApiException(String message) {
        super(message);
    }

    public CatApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatApiException(Throwable cause) {
        super(cause);
    }
}
