package com.pets.cats.infrastructure.adapter.in;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pets.cats.domain.exception.CatImageNotFoundException;
import com.pets.cats.domain.exception.CatBreedNotFoundException;
import com.pets.cats.domain.exception.CatApiException;
import com.pets.cats.domain.exception.CatApiConnectionException;
import com.pets.shared.exception.ErrorResponse;

@ControllerAdvice
public class CatExceptionHandler {

    @ExceptionHandler(CatImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCatImageNotFoundException(CatImageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la imagen de gato", ex.getMessage()));
    }

    @ExceptionHandler(CatBreedNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCatBreedNotFoundException(CatBreedNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la raza de gato", ex.getMessage()));
    }

    @ExceptionHandler(CatApiConnectionException.class)
    public ResponseEntity<ErrorResponse> handleCatApiConnectionException(CatApiConnectionException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(new ErrorResponse("Error de conexión con la API de gatos", ex.getMessage()));
    }

    @ExceptionHandler(CatApiException.class)
    public ResponseEntity<ErrorResponse> handleCatApiException(CatApiException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error en la API de gatos", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error interno del servidor", ex.getMessage()));
    }
}
