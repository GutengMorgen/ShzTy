package com.gutengmorgen.ShzTy.Infra.Errors;

public record DtoErrorResponse(
        String error,
        String message,
        int status,
        java.util.Date timestamp
) {
}
