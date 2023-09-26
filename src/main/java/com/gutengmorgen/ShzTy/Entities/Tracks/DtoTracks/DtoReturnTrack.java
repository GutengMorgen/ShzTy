package com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.Entities.Tracks.Track;

import java.sql.Date;
import java.util.Set;

public record DtoReturnTrack(
        String title,
        Date releaseDate,
        int playTime,
//        String url,
        String notes,
        Set<Genre> genreIDs,
        Album album,
        PlayList playList
) {

    public DtoReturnTrack(Track track){
        this(
                track.getTitle(),
                track.getReleaseDate(),
                track.getPlayTime(),
                track.getNotes(),
                track.getTrack_genres(),
                track.getTrackAlbum(),
                track.getPlayList()
        );
    }
}
