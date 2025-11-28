package com.pets.dogs.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pets.dogs.domain.exception.DogBreedNotFoundException;
import com.pets.dogs.domain.exception.DogImageNotFoundException;
import com.pets.dogs.domain.exception.DogApiException;

@ControllerAdvice
public class DogExceptionHandler {

    /**
     * Maneja la excepción cuando no se encuentra una imagen de perro
     * @param ex Excepción de imagen de perro no encontrada
     * @return ResponseEntity con el error y el mensaje
     */
    @ExceptionHandler(DogImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDogImageNotFoundException(DogImageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la imagen de perro", ex.getMessage()));
    }

    /**
     * Maneja la excepción cuando no se encuentra una raza de perro
     * @param ex Excepción de raza de perro no encontrada
     * @return ResponseEntity con el error y el mensaje
     */
    @ExceptionHandler(DogBreedNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDogBreedNotFoundException(DogBreedNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la raza de perro", ex.getMessage()));
    }

    /**
     * Maneja la excepción cuando ocurre un error en la API de perros
     * @param ex Excepción de error en la API de perros
     * @return ResponseEntity con el error y el mensaje
     */
    @ExceptionHandler(DogApiException.class)
    public ResponseEntity<ErrorResponse> handleDogApiException(DogApiException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error en la API de perros", ex.getMessage()));
    }

    /**
     * Maneja la excepción cuando ocurre un error genérico
     * @param e Excepción genérica
     * @return ResponseEntity con el error y el mensaje
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse error = new ErrorResponse(
            "INTERNAL_ERROR", 
            "Error interno del servidor: " + e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
