package com.gutengmorgen.ShzTy.Repositories;

import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
}
