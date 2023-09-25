package com.gutengmorgen.ShzTy.Entities.Tracks;

import com.gutengmorgen.ShzTy.Entities.Albums.Album;
import com.gutengmorgen.ShzTy.Entities.Artists.Artist;
import com.gutengmorgen.ShzTy.Entities.Genres.Genre;
import com.gutengmorgen.ShzTy.Entities.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoCreateTrack;
import com.gutengmorgen.ShzTy.Entities.Tracks.DtoTracks.DtoReturnTrack;
import com.gutengmorgen.ShzTy.Infra.Errors.GenreNotFoundException;
import com.gutengmorgen.ShzTy.Repositories.AlbumRepo;
import com.gutengmorgen.ShzTy.Repositories.GenreRepo;
import com.gutengmorgen.ShzTy.Repositories.PlayListRepo;
import com.gutengmorgen.ShzTy.Repositories.TrackRepo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TrackServiceImpl implements TrackServices{

    @Resource
    private TrackRepo trackRepository;
    @Resource
    private GenreRepo  genreRepository;
    @Resource
    private AlbumRepo  albumRepository;
    @Resource
    private PlayListRepo playListRepository;

    @Override
    public Track addTrack(DtoCreateTrack dto) {
        Track track = new Track(dto);

        associateGenres(dto.genreIDs(), track);

        Optional<Album> album = albumRepository.findById(dto.albumId());
        if(album.isEmpty()){
            throw new IllegalArgumentException("Artist not found");
        }

        Optional<PlayList> playList = playListRepository.findById(dto.playListId());
        if(playList.isEmpty()){
            throw new IllegalArgumentException("PlayList not found");
        }

        track.setAlbum(album.get());
        track.setPlayList(playList.get());
        trackRepository.save(track);
        return track;
    }

    @Override
    public List<DtoReturnTrack> getAllTracks() {
        return trackRepository.findAll().stream().map(DtoReturnTrack::new).toList();
    }

    @Override
    public DtoReturnTrack getTrackById(Long id) {
        return null;
    }

    @Override
    public DtoReturnTrack updateTrack(Long id, DtoCreateTrack dtoCreateTrack) {
        return null;
    }

    @Override
    public String deleteTrack(Long id) {
        return null;
    }

    private void associateGenres(Set<Long> genreIDs, Track track) {
        for (Long genreID : genreIDs) {
            Genre genre = genreRepository.findById(genreID)
                    .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with id %d not found", genreID)));
            track.addGenre(genre);
            genre.getTracks().add(track);
            genreRepository.save(genre);
        }
    }
}
