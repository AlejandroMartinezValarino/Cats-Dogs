package com.pets.dogs.domain.exception;

public class DogBreedNotFoundException extends DogApiException{
    public DogBreedNotFoundException(String message) {
        super("No se encontró la raza de perro: " + message);
    }

}
