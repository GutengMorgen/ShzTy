package com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class DtoUpdateAlbum {
    private String title;
    private Date releaseDate;
    private Long artistId;
    private Long albumFormatId;
    private Set<Long> genresId;
}
