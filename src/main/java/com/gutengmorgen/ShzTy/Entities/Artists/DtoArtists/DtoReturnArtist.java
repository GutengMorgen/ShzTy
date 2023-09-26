package com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists;

import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnSimpleAlbum;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record DtoReturnArtist(
        String Name,
        Date BornDate,
        String Gender,
        String Country,
        String Biography,
        int CountTracks,
        int CountAlbums,
        Set<String> Languages,
        Set<String> Genres,
        Set<DtoReturnSimpleAlbum> Albums
) {

    public DtoReturnArtist(Artist artist, int countTracks, int countAlbums, Set<DtoReturnSimpleAlbum> Albums){
        this(
                artist.getName(),
                artist.getBornDate(),
                artist.getGender(),
                artist.getCountry(),
                artist.getBiography(),
                countTracks,
                countAlbums,
                artist.getLanguages().stream().map(Language::getName).collect(Collectors.toSet()),
                artist.getGenres().stream().map(Genre::getName).collect(Collectors.toSet()),
                Albums
        );
    }
}
