package com.gutengmorgen.ShzTy.Repositories;

import com.gutengmorgen.ShzTy.Entities.PlayLists.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepo extends JpaRepository<PlayList, Long> {
}
