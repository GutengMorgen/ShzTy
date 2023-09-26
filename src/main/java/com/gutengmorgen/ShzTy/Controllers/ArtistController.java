package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Artists.ArtistServices;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoUpdateArtist;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/shzty/artists")
public class ArtistController {

    private final ArtistServices services;

    public ArtistController(ArtistServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<DtoReturnArtist>> list() {
        List<DtoReturnArtist> dtoReturnArtists = services.getAllArtists();
        return ResponseEntity.ok(dtoReturnArtists);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DtoReturnArtist> findById(@PathVariable Long id) {
        DtoReturnArtist artist = services.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public ResponseEntity<Artist> create(@Valid @RequestBody DtoCreateArtist dtoCreateArtist) {
        Artist addedArtist = services.addArtist(dtoCreateArtist);

//        URI location = URI.create("/shzty/artists/" + addedArtist.getId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedArtist.getId()).toUri();

//        return new ResponseEntity<>(addedArtist, HttpStatus.CREATED);
        return ResponseEntity.created(location).body(addedArtist);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DtoReturnArtist> update(@PathVariable Long id,
                                  @Valid @RequestBody DtoUpdateArtist dtoUpdateArtist) {
        DtoReturnArtist dtoReturnArtist = services.updateArtist(id, dtoUpdateArtist);
        return ResponseEntity.ok(dtoReturnArtist);
    }
}
