package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import com.gutengmorgen.ShzTy.Infra.Errors.DuplicateArtistException;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import com.gutengmorgen.ShzTy.Repositories.LanguageRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistServices{

    @Autowired ArtistRepo artistRepository;
    @Autowired GenreRepo genreRepository;
    @Autowired LanguageRepo languageRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Artist addArtist(DtoCreateArtist dto) {
        ValidateArtistName(dto.Name());
        Artist artist = new Artist(dto);

        associateLanguages(dto.LanguageIDs(), artist);
        associateGenres(dto.GenreIDs(), artist);

        artistRepository.save(artist);
        return artist;
    }

    @Override
    public List<DtoReturnArtist> getAllArtists() {
        return artistRepository.findAll().stream().map(DtoReturnArtist::new).toList();
    }

    @Override
    public DtoReturnArtist getArtistById(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Artist with id %d not found", id)));

        return new DtoReturnArtist(artist);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DtoReturnArtist updateArtist(Long id, DtoUpdateArtist dtoUpdateArtist) {
        Artist artist = artistRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Artist with id %d not found", id)));

        ValidateArtistName(dtoUpdateArtist.Name());
        artist.update(dtoUpdateArtist);

        if(dtoUpdateArtist.LanguageIDs() != null){
            artist.getLanguages().clear();
            associateLanguages(dtoUpdateArtist.LanguageIDs(), artist);
        }
        if(dtoUpdateArtist.GenreIDs() != null){
            artist.getGenres().clear();
            associateGenres(dtoUpdateArtist.GenreIDs(), artist);
        }

        artistRepository.save(artist);
        return new DtoReturnArtist(artist);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public String deleteArtist(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Artist with id %d not found", id)));
        artist.removeGenres();
        artist.removeLanguages();
        artistRepository.delete(artist);
        return String.format("Artist with id %d successfully deleted", id);
    }

    private void ValidateArtistName(String name){
        if(artistRepository.existsByName(name))
            throw new DuplicateArtistException(String.format("Artist with name %s already exists", name));
    }

    private void associateLanguages(Set<Long> languageIDs, Artist artist) {
        for (Long languageID : languageIDs) {
            Language language = languageRepository.findById(languageID).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Language with id %d not found", languageID)));
            artist.addLanguage(language);
            language.getArtists().add(artist);
            languageRepository.save(language);
        }
    }

    private void associateGenres(Set<Long> genreIDs, Artist artist) {
        for (Long genreID : genreIDs) {
            Genre genre = genreRepository.findById(genreID).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Genre with id %d not found", genreID)));
            artist.addGenre(genre);
            genre.getArtists().add(artist);
            genreRepository.save(genre);
        }
    }
}
