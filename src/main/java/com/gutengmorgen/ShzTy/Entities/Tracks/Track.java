package com.gutengmorgen.ShzTy.Entities.Tracks;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoCreateTrack;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tracks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tracks")
    private Long id;
    private String title;
    private Date releaseDate;
    private int playTime;
    private String notes;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "tracks_genres", joinColumns = @JoinColumn(name = "track_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> Track_genres = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_albums")
    private Album album;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_playlists")
    private PlayList playList;

    public void addGenre(Genre genre) {
        this.Track_genres.add(genre);
        genre.getTracks().add(this);
    }
    public void removeGenre(Genre genre) {
        this.Track_genres.remove(genre);
        genre.getTracks().remove(this);
    }

    public Track(DtoCreateTrack dto) {
        this.title = dto.title();
        this.releaseDate = dto.releaseDate();
        this.playTime = dto.playTime();
        this.notes = dto.notes();
    }
}
