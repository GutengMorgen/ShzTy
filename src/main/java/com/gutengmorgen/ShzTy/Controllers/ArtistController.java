package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Artists.ArtistServices;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<Iterable<DtoReturnArtist>> list() {
        List<DtoReturnArtist> dtoReturnArtists = services.getAllArtists();
//        return new ResponseEntity<>(artistList, HttpStatus.OK);
        return ResponseEntity.ok(dtoReturnArtists);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DtoReturnArtist> findById(@PathVariable Long id) {
        DtoReturnArtist artist = services.getArtistById(id);

        if(artist == null){
            String errorMessage = String.format("Item not found with ID: %d", id);
            return ResponseEntity.notFound().header("Error", errorMessage).build();
        }
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public ResponseEntity<Artist> create(@Valid @RequestBody DtoCreateArtist dtoCreateArtist) {
        Artist addedArtist = services.addArtist(dtoCreateArtist);
        return new ResponseEntity<>(addedArtist, HttpStatus.CREATED);
//        return ResponseEntity.created(null).body(addedArtist);
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
