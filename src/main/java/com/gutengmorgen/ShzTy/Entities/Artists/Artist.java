package com.gutengmorgen.ShzTy.Entities.Artists;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Name;
    private Date BornDate;
    private String Gender;
    private String Country;
    private String biography;

    @ManyToMany
    @JoinColumn(name = "language_id")
    @JsonBackReference
    private Language language;

    @ManyToMany
    @JoinColumn(name = "genre_id")
    @JsonBackReference
    private Genre genre;
}
