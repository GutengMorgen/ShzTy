package com.gutengmorgen.ShzTy.Infra.Errors;

public class DuplicateArtistException extends RuntimeException {
    public DuplicateArtistException(String message) {
        super(message);
    }
}
