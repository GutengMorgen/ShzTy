package com.gutengmorgen.ShzTy.Entities.Albums;

import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoUpdateAlbum;

import java.util.List;

public interface AlbumServices {
    Album addAlbum(DtoCreateAlbum album);
    DtoReturnAlbum getAlbum(Long id);
    Album updateAlbum(Long id, DtoUpdateAlbum album);
    Album deleteAlbum(Long id);
    List<DtoReturnAlbum> getAllAlbums();
}
