package com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums;

import com.gutengmorgen.ShzTy.Entities.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Tracks.Track;

import java.sql.Date;
import java.util.Set;

public record DtoReturnAlbum(
        String title,
        Date releaseDate,
        Long artistId,
        String albumFormat,
        Set<Genre> genres,
        Set<Track> tracks
) {

    public DtoReturnAlbum(Album album) {
        this(
                album.getTitle(),
                album.getReleaseDate(),
                album.getArtist().getId(),
                album.getAlbumFormat().getName(),
                album.getAlbum_genres(),
                album.getTracks()
        );
    }
}
