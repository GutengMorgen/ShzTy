package com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record DtoReturnSimpleAlbum(
        String title,
        Date releaseDate,
        String albumFormat,
        int countTracks,
        int playTime,
        Set<String> genres) {

    public DtoReturnSimpleAlbum(Album album) {
        this(
                album.getTitle(),
                album.getReleaseDate(),
                album.getAlbumFormat().getName(),
                album.tracksCount(),
                album.playTime(),
                album.getAlbum_genres().stream().map(Genre::getName).collect(Collectors.toSet())
        );
    }
}
