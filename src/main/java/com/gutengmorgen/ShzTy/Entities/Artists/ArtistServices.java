package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;

import java.util.List;

public interface ArtistServices {
    public Artist addArtist(DtoCreateArtist dtoCreateArtist);
    public List<DtoReturnArtist> getAllArtists();
    public DtoReturnArtist getArtistById(Long id);
    public DtoCreateArtist updateArtist(Long id, DtoCreateArtist dtoCreateArtist);
    public String deleteArtist(Long id);

}
