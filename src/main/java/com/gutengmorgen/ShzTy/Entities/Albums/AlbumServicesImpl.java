package com.gutengmorgen.ShzTy.Entities.Albums;

import com.gutengmorgen.ShzTy.Entities.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.Entities.Albums.DtoAlbums.DtoUpdateAlbum;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Infra.Errors.GenreNotFoundException;
import com.gutengmorgen.ShzTy.Repositories.*;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AlbumServicesImpl implements AlbumServices {

    @Resource AlbumRepo albumRepository;
    @Resource ArtistRepo artistRepository;
    @Resource AlbumFormatRepo albumFormatRepository;
    @Resource GenreRepo genreRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Album addAlbum(DtoCreateAlbum dto) {
        Album album = new Album(dto);

        Artist artist = artistRepository.findById(dto.artistId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Artist with id %d not found", dto.artistId())));

        AlbumFormat albumFormat = albumFormatRepository.findById(dto.albumFormatId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("AlbumFormat with id %d not found", dto.albumFormatId())));

        associateGenres(dto.genresId(), album);

        album.setArtist(artist);
        album.setAlbumFormat(albumFormat);
        albumRepository.save(album);
        return album;
    }

    @Override
    public List<DtoReturnAlbum> getAllAlbums() {
        return albumRepository.findAll().stream().map(DtoReturnAlbum::new).toList();
    }

    @Override
    public DtoReturnAlbum getAlbum(Long id) {
        Album  album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Album with id %d not found", id)));

        return new DtoReturnAlbum(album);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Album updateAlbum(Long id, DtoUpdateAlbum dto) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Album with id %d not found", id)));

        if(dto.artistId() != null){
            Artist artist = artistRepository.findById(dto.artistId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Artist with id %d not found", dto.artistId())));

            album.setArtist(artist);
        }

        if(dto.albumFormatId() != null) {
            AlbumFormat albumFormat = albumFormatRepository.findById(dto.albumFormatId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("AlbumFormat with id %d not found", dto.albumFormatId())));

            album.setAlbumFormat(albumFormat);
        }

        if(dto.genresId() != null) {
            associateGenres(dto.genresId(), album);
        }

        album.update(dto);
        albumRepository.save(album);
        return album;
    }

    @Override
    public Album deleteAlbum(Long id) {
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
