package com.pets.cats.domain.exception;

public class CatBreedNotFoundException extends CatApiException{

    public CatBreedNotFoundException(String message) {
        super("No se encontró la raza de gato "+message);
    }

    public CatBreedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatBreedNotFoundException(Throwable cause) {
        super(cause);
    }
}
