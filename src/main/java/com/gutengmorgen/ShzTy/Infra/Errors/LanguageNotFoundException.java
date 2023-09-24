package com.gutengmorgen.ShzTy.Infra.Errors;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(String message) {
        super(message);
    }
}
