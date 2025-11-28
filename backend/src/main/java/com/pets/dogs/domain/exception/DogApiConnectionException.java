package com.pets.dogs.domain.exception;

public class DogApiConnectionException extends DogApiException{
    public DogApiConnectionException(String message) {
        super("Error al conectar con la API de perros: " + message);
    }

}
