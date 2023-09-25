package com.gutengmorgen.ShzTy.Repositories;

import com.gutengmorgen.ShzTy.Entities.Tracks.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {
}
