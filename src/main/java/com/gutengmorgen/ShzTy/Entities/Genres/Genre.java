package com.gutengmorgen.ShzTy.Entities.Genres;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genres")
    private Long Id;

//    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Artist> artists;
}
