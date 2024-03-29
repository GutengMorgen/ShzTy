package com.gutengmorgen.ShzTy.Entities.AlbumFormats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "album_formats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album_formats")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "albumFormat")
    @JsonIgnore
    private Set<Album> albums;
}
