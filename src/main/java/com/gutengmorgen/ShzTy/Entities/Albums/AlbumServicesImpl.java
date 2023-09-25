package com.gutengmorgen.ShzTy.Entities.Albums;

import com.gutengmorgen.ShzTy.Entities.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Infra.Errors.GenreNotFoundException;
import com.gutengmorgen.ShzTy.Repositories.AlbumFormatRepo;
import com.gutengmorgen.ShzTy.Repositories.AlbumRepo;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AlbumServicesImpl implements AlbumServices {

    @Resource
    private AlbumRepo albumRepository;
    @Resource
    private ArtistRepo artistRepository;
    @Resource
    private AlbumFormatRepo albumFormatRepository;
    @Resource
    private GenreRepo genreRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Album addAlbum(DtoCreateAlbum dto) {
        Album album = new Album(dto);

        Optional<Artist> artist = artistRepository.findById(dto.artistId());
        if (artist.isEmpty()) {
            throw new IllegalArgumentException("Artist not found");
        }

        Optional<AlbumFormat> albumFormat = albumFormatRepository.findById(dto.albumFormatId());
        if (albumFormat.isEmpty()) {
            throw new IllegalArgumentException("albumFormat not found");
        }

        associateGenres(dto.genresId(), album);

        album.setArtist(artist.get());
        album.setFormat(albumFormat.get());
        albumRepository.save(album);
        return album;
    }

    @Override
    public Album getAlbum(Long id) {
        return null;
    }

    @Override
    public Album updateAlbum(Album album) {
        return null;
    }

    @Override
    public Album deleteAlbum(Long id) {
        return null;
    }

    @Override
    public Iterable<Album> getAllAlbums() {
        return null;
    }

    private void associateGenres(Set<Long> genreIDs, Album album) {
        for (Long genreID : genreIDs) {
            Genre genre = genreRepository.findById(genreID)
                    .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with id %d not found", genreID)));
            album.addGenre(genre);
            genre.getAlbums().add(album);
            genreRepository.save(genre);
        }
    }
}
