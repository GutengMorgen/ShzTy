package com.gutengmorgen.ShzTy.Repositories;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
}
