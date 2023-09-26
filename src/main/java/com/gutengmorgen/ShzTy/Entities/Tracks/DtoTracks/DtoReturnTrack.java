package com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.Entities.Tracks.Track;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

public record DtoReturnTrack(
        String title,
        Date releaseDate,
        int playTime,
//        String url,
        String notes,
        Set<String> genresName,
        String albumTitle,
        String playListTitle
) {

    public DtoReturnTrack(Track track){
        this(
                track.getTitle(),
                track.getReleaseDate(),
                track.getPlayTime(),
                track.getNotes(),
                track.getTrack_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
                track.getTrackAlbum().getTitle(),
                track.getPlayList().getTile()
        );
    }

//    public static DtoReturnTrack simple(Track track){
//        return new DtoReturnTrack(
//                track.getTitle(),
//                track.getReleaseDate(),
//                track.getPlayTime(),
//                track.getTrack_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
//                track.getPlayList().getTile()
//        );
//    }


//    public static DtoReturnTrack simple(Track track){
//        return new DtoReturnTrack(
//                track.getTitle(),
//                track.getReleaseDate(),
//                track.getPlayTime(),
//                track.getNotes(),
//                track.getTrack_genres().stream().map(Genre::getName).collect(Collectors.toSet()),
//                track.getTrackAlbum().getTitle(),
//                track.getPlayList().getTile(),
//                track.getCredits(),
//                track.getReview()
//        );
//    }
}
