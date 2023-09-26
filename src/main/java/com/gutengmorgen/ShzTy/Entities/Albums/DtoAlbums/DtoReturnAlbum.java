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
        int countTracks,
        int playTime,
        Set<String> genres,
        Set<DtoReturnSimpleTrack> tracks
) {

    public DtoReturnAlbum(Album album, int playTime) {
        this(
                album.getTitle(),
                album.getReleaseDate(),
                album.getAlbumFormat().getName(),
                album.getTracks().size(),
                playTime,
                album.getAlbum_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
                album.getTracks().stream().map(DtoReturnSimpleTrack::new).collect(Collectors.toSet())
        );
    }
}
