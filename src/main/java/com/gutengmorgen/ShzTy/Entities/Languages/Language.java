package com.gutengmorgen.ShzTy.Entities.Languages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_languages")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "languages")
    @JsonIgnore
    private Set<Artist> artists;
}
