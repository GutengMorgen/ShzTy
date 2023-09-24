package com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

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
        Set<Long> LanguageIDs,
        Set<Long> GenreIDs
        //agregar los dto para retornar el numero de canciones y todos los albums
) {
}
