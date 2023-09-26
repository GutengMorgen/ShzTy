package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Albums.AlbumServices;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoUpdateAlbum;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("shzty/albums")
public class AlbumController {

    private final AlbumServices services;

    public AlbumController(AlbumServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<DtoReturnAlbum>> list() {
        List<DtoReturnAlbum> dtoReturnAlbums = services.getAllAlbums();
        return ResponseEntity.ok(dtoReturnAlbums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoReturnAlbum> get(@PathVariable Long id) {
        DtoReturnAlbum dtoReturnAlbum = services.getAlbum(id);
        return ResponseEntity.ok(dtoReturnAlbum);
    }

    @PutMapping
    public ResponseEntity<Album> create(@RequestBody @Valid DtoCreateAlbum dto) {
        Album album = services.addAlbum(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(album.getId()).toUri();

        return ResponseEntity.created(location).body(album);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable Long id, @RequestBody @Valid DtoUpdateAlbum dto) {
        Album album = services.updateAlbum(id, dto);
        return ResponseEntity.ok(album);
    }
}
