package com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public record DtoReturnArtist(
        String Name,
        Date BornDate,
        String Gender,
        String Country,
        String Biography,
        Set<Language> LanguageIDs,
        Set<Genre> GenreIDs,
        Long CountTracks,
        Long CountAlbums,
        Set<Album> Albums
) {

    public static DtoReturnArtist testing(Artist artist, Long countAlbums){
        return new DtoReturnArtist(
                artist.getName(),
                artist.getBornDate(),
                artist.getGender(),
                artist.getCountry(),
                artist.getBiography(),
                Set.of(),
                Set.of(),
                0L,
                countAlbums,
                artist.getAlbums()
        );
    }
}
