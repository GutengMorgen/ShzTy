package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private Long id;
    @Column(unique = true)
    private String name;
    private Date bornDate;
    private String gender;
    private String country;
    private String biography;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "artists_languages", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "artists_genres", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "artist")
    private Set<Album> albums;

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getArtists().add(this);
    }
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getArtists().remove(this);
    }
    public void removeGenres() {
        for (Genre genre : new HashSet<>(genres)) {
            removeGenre(genre);
        }
    }

    public void addLanguage(Language language) {
        this.languages.add(language);
        language.getArtists().add(this);
    }
    public void removeLanguage(Language language) {
        this.languages.remove(language);
        language.getArtists().remove(this);
    }

    public Artist(DtoCreateArtist dto){
        this.name = dto.Name();
        this.bornDate = dto.BornDate();
        this.gender = dto.Gender();
        this.country = dto.Country();
        this.biography = dto.Biography();
    }

    public void update(DtoUpdateArtist dto){
        if(dto.Name() != null) this.name = dto.Name();
        if(dto.BornDate() != null) this.bornDate = dto.BornDate();
        if(dto.Gender() != null) this.gender = dto.Gender();
        if(dto.Country() != null) this.country = dto.Country();
        if(dto.Biography() != null) this.biography = dto.Biography();
    }

    public void removeLanguages() {
        for (Language language : new HashSet<>(languages)) {
            removeLanguage(language);
        }
    }
}
