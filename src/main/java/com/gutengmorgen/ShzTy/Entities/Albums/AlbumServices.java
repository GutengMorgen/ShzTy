package com.gutengmorgen.ShzTy.Entities.Albums;

import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoUpdateAlbum;
import com.gutengmorgen.ShzTy.Repositories.AlbumRepo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AlbumServices {
    public Album addAlbum(DtoCreateAlbum album);
    public DtoReturnAlbum getAlbum(Long id);
    public Album updateAlbum(Long id, DtoUpdateAlbum album);
    public Album deleteAlbum(Long id);
    public List<DtoReturnAlbum> getAllAlbums();
}
