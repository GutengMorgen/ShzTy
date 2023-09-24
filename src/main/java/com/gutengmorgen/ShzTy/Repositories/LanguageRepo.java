package com.gutengmorgen.ShzTy.Repositories;

import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Long> {
}
