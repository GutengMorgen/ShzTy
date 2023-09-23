package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistServices{

    @Resource
    private ArtistRepo artistRepository;
    @Resource
    private GenreRepo genreRepository;

    @Transactional
    @Override
    public Artist addArtist(DtoCreateArtist dto) {
        Artist artist = new Artist(dto);
//        Optional<Artist> artistOptional = artistRepository.findByName(artist.getName());
//        if (artistOptional.isPresent()) {
//            throw new IllegalStateException("Artist already exists");
//        }
        for (Long genreID : dto.GenreIDs()) {
            Genre genre = genreRepository.findById(genreID).orElse(null);
            if (genre != null) {
                artist.addGenre(genre);
                genre.getArtists().add(artist);
                genreRepository.save(genre);
                artistRepository.save(artist);
            }


//            if (genreOptional.isPresent()) {
//                artist.addGenre(genreOptional.get());
//                genreOptional.get().getArtists().add(artist);
//                genreRepository.save(genreOptional.get());
//                artistRepository.save(artist);
//            }
//            else
//                throw new IllegalStateException("Genre does not exist");
        }
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

//    private void mapDtoToArtist(DtoCreateArtist dtoCreateArtist, Artist artist) {
//        artist.setName(dtoCreateArtist.Name());
//        dtoCreateArtist.genre().stream().forEach(genreID -> {
//            Genre genre = genreRepository.findById(genreID).orElse(null);
//            if (null == genre) {
//                genre = new Genre();
//                genre.setArtists(new HashSet<>());
//            }
//            genre.setId(genreID);
//            artist.addGenre(genre);
//        });
//    }

//    private DtoCreateArtist mapEntityToDto(Artist artist) {
//        DtoCreateArtist responseDto = new DtoCreateArtist();
//        responseDto.setName(student.getName());
//        responseDto.setId(student.getId());
//        responseDto.setCourses(student.getCourses().stream().map(Course::getName).collect(Collectors.toSet()));
//        return responseDto;
//    }
}
