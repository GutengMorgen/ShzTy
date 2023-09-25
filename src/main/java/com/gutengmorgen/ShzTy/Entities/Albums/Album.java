package com.gutengmorgen.ShzTy.Entities.Albums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gutengmorgen.ShzTy.Entities.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_albums")
    private Long id;
    private String title;
    private Date releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artists")
    @JsonIgnore
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album_formats")
    @JsonIgnore
    private AlbumFormat format;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "albums_genres",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<Genre> Album_genres = new HashSet<>();

    public void addGenre(Genre genre) {
        this.Album_genres.add(genre);
        genre.getAlbums().add(this);
    }
    public void removeGenre(Genre genre) {
        this.Album_genres.remove(genre);
        genre.getAlbums().remove(this);
    }

    public Album(DtoCreateAlbum dto){
        this.title = dto.title();
        this.releaseDate = dto.releaseDate();
    }
}
