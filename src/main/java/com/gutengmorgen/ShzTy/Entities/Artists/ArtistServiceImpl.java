package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import com.gutengmorgen.ShzTy.Infra.Errors.DuplicateArtistException;
import com.gutengmorgen.ShzTy.Infra.Errors.GenreNotFoundException;
import com.gutengmorgen.ShzTy.Infra.Errors.LanguageNotFoundException;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import com.gutengmorgen.ShzTy.Repositories.LanguageRepo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistServices{

    @Resource
    private ArtistRepo artistRepository;
    @Resource
    private GenreRepo genreRepository;
    @Resource
    private LanguageRepo languageRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Artist addArtist(DtoCreateArtist dto) {
        Artist artist = new Artist(dto);

        boolean artistExists = artistRepository.existsByName(dto.Name());
        if (artistExists) {
            throw new DuplicateArtistException("'Artist with name %s already exists'".formatted(dto.Name()));
        }

        for (Long languageID : dto.LanguageIDs()){
            Language language = languageRepository.findById(languageID).orElseThrow(
                    () -> new LanguageNotFoundException("'Language with id %d not found'".formatted(languageID))
            );

            artist.addLanguage(language);
            language.getArtists().add(artist);
            languageRepository.save(language);

        }

        for (Long genreID : dto.GenreIDs()) {
            Genre genre = genreRepository.findById(genreID).orElseThrow(
                    () -> new GenreNotFoundException("'Genre with id %d not found'".formatted(genreID))
            );

            artist.addGenre(genre);
            genre.getArtists().add(artist);
            genreRepository.save(genre);
        }

        artistRepository.save(artist);
        return artist;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public DtoCreateArtist getArtistById(Long id) {
        return null;
    }

    @Override
    public DtoCreateArtist updateArtist(Long id, DtoCreateArtist dtoCreateArtist) {
        return null;
    }

    @Override
    public String deleteArtist(Long id) {
        return null;
    }
}
