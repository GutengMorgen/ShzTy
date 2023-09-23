package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;

import java.util.List;

public interface ArtistServices {
    public Artist addArtist(DtoCreateArtist dtoCreateArtist);
    public List<Artist> getAllArtists();
    public DtoCreateArtist getArtistById(Long id);
    public DtoCreateArtist updateArtist(Long id, DtoCreateArtist dtoCreateArtist);
    public String deleteArtist(Long id);

}
