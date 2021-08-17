package com.test.pruebaGestioLogistica.exceptions;

public class DontFindElementException extends BadRequestException {

    public  static final String DESCRIPCION = "Don't find any element";

    public DontFindElementException(String message) {
        super(DESCRIPCION + ". " + message);
    }
}
