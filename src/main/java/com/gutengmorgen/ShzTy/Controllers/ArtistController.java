package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shzty/artists")
public class ArtistController {

    private final ArtistRepo repository;

    public ArtistController(ArtistRepo repository) {
        this.repository = repository;
    }


    @GetMapping
    public String list() {
        return "Artists";
    }

    @GetMapping(path = "/{id}")
    public String findById() {
        return "Artist";
    }

    @PostMapping
    public String create() {
        return "Artist";
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
