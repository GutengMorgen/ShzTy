package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artists")
    private Long Id;
    private String Name;
    private Date BornDate;
    private String Gender;
    private String Country;
    private String biography;

//    @JoinColumn(name = "genre_id")
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "artists_genres",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<Genre> genres = new HashSet<>();


    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getArtists().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getArtists().remove(this);
    }

    public Artist(DtoCreateArtist dto){
        this.Name = dto.Name();
        this.BornDate = dto.BornDate();
        this.Gender = dto.Gender();
        this.Country = dto.Country();
        this.biography = dto.Biography();
    }
}
