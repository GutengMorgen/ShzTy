package com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks;

import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Tracks.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class DtoReturnSimpleTrack {
    private String title;
    private Date releaseDate;
    private int playTime;
    private String playListTitle;
    private Set<String> genresName;


    public DtoReturnSimpleTrack(Track track) {
        this(
                track.getTitle(),
                track.getReleaseDate(),
                track.getPlayTime(),
                track.getPlayList().getTile(),
                track.getTrack_genres().stream().map(Genre::getName).collect(Collectors.toSet())
        );
    }
}
