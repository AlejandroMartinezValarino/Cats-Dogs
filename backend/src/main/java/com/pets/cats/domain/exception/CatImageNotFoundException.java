package com.pets.cats.domain.exception;

public class CatImageNotFoundException extends CatApiException{

    public CatImageNotFoundException(String message) {
        super(message);
    }

    public CatImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatImageNotFoundException(Throwable cause) {
        super(cause);
    }
}
