package com.gutengmorgen.ShzTy.Infra.Errors;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
