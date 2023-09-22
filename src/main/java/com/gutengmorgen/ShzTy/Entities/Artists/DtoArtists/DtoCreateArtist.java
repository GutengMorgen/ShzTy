package com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DtoCreateArtist(
        @NotBlank(message = "Name is required")
        String Name,
        Date BornDate,
        @NotBlank(message = "Gender is required")
        String Gender,
        String Country,
        String Biography,
        @NotBlank(message = "language is required")
        int language,
        @NotBlank(message = "genre is required")
        int genre
) {
}
