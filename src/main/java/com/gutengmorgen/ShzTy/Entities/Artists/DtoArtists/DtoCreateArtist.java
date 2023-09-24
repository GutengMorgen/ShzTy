package com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.Set;

public record DtoCreateArtist(
        @NotBlank(message = "Name is required")
        String Name,
        Date BornDate,
        @NotBlank(message = "Gender is required")
        String Gender,
        String Country,
        String Biography,
        Set<Long> GenreIDs
) {
}
