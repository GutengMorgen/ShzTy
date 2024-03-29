package com.gutengmorgen.ShzTy.Entities.Artists;

import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.Entities.Artists.DtoArtists.DtoUpdateArtist;

import java.util.List;

public interface ArtistServices {
    public Artist addArtist(DtoCreateArtist dtoCreateArtist);
    public List<DtoReturnArtist> getAllArtists();
    public DtoReturnArtist getArtistById(Long id);
    public DtoReturnArtist updateArtist(Long id, DtoUpdateArtist dtoUpdateArtist);
    public String deleteArtist(Long id);

}
