package com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoReturnSimpleTrack;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record DtoReturnAlbum(
        String title,
        Date releaseDate,
        String albumFormat,
        Set<String> genres,
        Set<DtoReturnSimpleTrack> tracks
) {

    public DtoReturnAlbum(Album album) {
        this(
                album.getTitle(),
                album.getReleaseDate(),
                album.getAlbumFormat().getName(),
                album.getAlbum_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
                album.getTracks().stream().map(DtoReturnSimpleTrack::new).collect(Collectors.toSet())
        );
    }

    public static DtoReturnAlbum without_Tracks(Album album) {
        return new DtoReturnAlbum(
                album.getTitle(),
                album.getReleaseDate(),
                album.getAlbumFormat().getName(),
                album.getAlbum_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
                Set.of()
        );
    }
}
