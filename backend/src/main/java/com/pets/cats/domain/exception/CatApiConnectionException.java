package com.pets.cats.domain.exception;

public class CatApiConnectionException extends CatApiException{
    public CatApiConnectionException(String message) {
        super("Error al conectar con la API de gatos: "+message);
    }

    public CatApiConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatApiConnectionException(Throwable cause) {
        super(cause);
    }

}
