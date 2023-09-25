package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.Languages.Language;
import com.gutengmorgen.ShzTy.Infra.Errors.DuplicateArtistException;
import com.gutengmorgen.ShzTy.Infra.Errors.GenreNotFoundException;
import com.gutengmorgen.ShzTy.Infra.Errors.LanguageNotFoundException;
import com.gutengmorgen.ShzTy.Repositories.AlbumRepo;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import com.gutengmorgen.ShzTy.Repositories.LanguageRepo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistServices{

    @Resource
    private ArtistRepo artistRepository;
    @Resource
    private GenreRepo genreRepository;
    @Resource
    private LanguageRepo languageRepository;
//    @Resource
//    private AlbumRepo albumRepository;

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
        return artistRepository.findAll().stream().map(artist -> {
            int countAlbums = artist.getAlbums().size();
//            Long countAlbumss = albumRepository.countByArtist(artist);
            return DtoReturnArtist.testing(artist, (long) countAlbums);
        }).toList();
    }

    @Override
    public DtoReturnArtist getArtistById(Long id) {
//        return artistRepository.findById(id).map(DtoReturnArtist::new).orElse(null);
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

    private void ValidateArtistName(String name){
        if(artistRepository.existsByName(name))
            throw new DuplicateArtistException(String.format("Artist with name %s already exists", name));
    }

    private void associateLanguages(Set<Long> languageIDs, Artist artist) {
        for (Long languageID : languageIDs) {
            Language language = languageRepository.findById(languageID)
                    .orElseThrow(() -> new LanguageNotFoundException(String.format("Language with id %d not found", languageID)));
            artist.addLanguage(language);
            language.getArtists().add(artist);
            languageRepository.save(language);
        }
    }

    private void associateGenres(Set<Long> genreIDs, Artist artist) {
        for (Long genreID : genreIDs) {
            Genre genre = genreRepository.findById(genreID)
                    .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with id %d not found", genreID)));
            artist.addGenre(genre);
            genre.getArtists().add(artist);
            genreRepository.save(genre);
        }
    }
}
