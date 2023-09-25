package com.gutengmorgen.ShzTy.Entities.Tracks;

import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoCreateTrack;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoReturnTrack;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TrackServices {
    public Track addTrack(DtoCreateTrack dtoCreateTrack);
    public List<DtoReturnTrack> getAllTracks();
    public DtoReturnTrack getTrackById(Long id);
    public DtoReturnTrack updateTrack(Long id, DtoCreateTrack dtoCreateTrack);
    public String deleteTrack(Long id);
}
