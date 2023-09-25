package com.gutengmorgen.ShzTy.Controllers;

import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoCreateTrack;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoReturnTrack;
import com.gutengmorgen.ShzTy.Entities.Tracks.Track;
import com.gutengmorgen.ShzTy.Entities.Tracks.TrackServices;
import com.gutengmorgen.ShzTy.Repositories.TrackRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shzty/track")
public class TrackController {

    private final TrackServices services;

    public TrackController(TrackServices services) {
        this.services = services;
    }

    @GetMapping
    public Iterable<DtoReturnTrack> getAllTracks() {
        return services.getAllTracks();
    }

    @PostMapping
    public ResponseEntity<Track> addTrack(@Valid @RequestBody DtoCreateTrack dto) {
        Track track = services.addTrack(dto);
        return new ResponseEntity<>(track, HttpStatus.CREATED);
    }
}
