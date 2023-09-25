package com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.Set;

public record DtoCreateTrack(
        @NotBlank
        String title,
        @NotNull
        Date releaseDate,
        @NotNull
        int playTime,
//        String url,
        String notes,
        Set<Long> genreIDs,
        @NotNull
        Long albumId,
        @NotNull
        Long playListId
) {
}
