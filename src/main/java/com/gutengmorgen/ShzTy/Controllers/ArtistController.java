package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Artists.ArtistServices;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/shzty/artists")
public class ArtistController {

    private final ArtistServices services;

    public ArtistController(ArtistServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<Iterable<Artist>> list() {
        List<Artist> artistList = services.getAllArtists();
        return new ResponseEntity<>(artistList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public String findById() {
        return "Artist";
    }

    @PostMapping
    public ResponseEntity<Artist> create(@Valid @RequestBody DtoCreateArtist dtoCreateArtist) {
        Artist addedArtist = services.addArtist(dtoCreateArtist);
        return new ResponseEntity<>(addedArtist, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public String update() {
        return "Artist";
    }

    @DeleteMapping(path = "/{id}")
    public String delete() {
        return "Artist";
    }
}
