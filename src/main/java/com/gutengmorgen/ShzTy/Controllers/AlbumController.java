package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Albums.AlbumServices;
import com.gutengmorgen.ShzTy.Entities.Albums.AlbumServicesImpl;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Repositories.AlbumRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shzty/albums")
public class AlbumController {

    private final AlbumServices services;

    public AlbumController(AlbumServices services) {
        this.services = services;
    }

    @GetMapping
    public String list() {
        return "List of albums";
    }

    @PutMapping
    public ResponseEntity<Album> create(@RequestBody @Valid DtoCreateAlbum dto) {
        Album album = services.addAlbum(dto);
        return ResponseEntity.ok(album);
    }
}
