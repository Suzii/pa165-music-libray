package cz.muni.fi.pa165.musiclib.facade;

import cz.muni.fi.pa165.musiclib.dto.SongAddYoutubeLinkDTO;
import cz.muni.fi.pa165.musiclib.dto.SongCreateDTO;
import cz.muni.fi.pa165.musiclib.dto.SongDTO;
import cz.muni.fi.pa165.musiclib.entity.Song;
import cz.muni.fi.pa165.musiclib.exception.MusicLibServiceException;
import cz.muni.fi.pa165.musiclib.service.AlbumService;
import cz.muni.fi.pa165.musiclib.service.BeanMappingService;
import cz.muni.fi.pa165.musiclib.service.GenreService;
import cz.muni.fi.pa165.musiclib.service.MusicianService;
import cz.muni.fi.pa165.musiclib.service.SongService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author zdankovc
 * @version 22/11/2015
 */
@Service
@Transactional
public class SongFacadeImpl implements SongFacade {

    @Inject
    private BeanMappingService beanMappingService;
    
    @Inject
    private SongService songService; 
    
    @Inject
    private AlbumService albumService;
    
    @Inject
    private GenreService genreService;
    
    @Inject
    private MusicianService musicianService; 
    
    @Override
    public Long create(SongCreateDTO song, Long albumId) {
        Song newSong = new Song();
        newSong.setTitle(song.getTitle());
        newSong.setCommentary(song.getCommentary());
        newSong.setBitrate(song.getBitrate());
        newSong.setGenre(genreService.findById(song.getGenreId()));
        newSong.setMusician(musicianService.findById(song.getMusicianId()));
        newSong.setAlbum(albumService.findById(albumId));
        
        songService.create(newSong);
        return newSong.getId();
    }    
    
    @Override
    public void addYoutubeLink(SongAddYoutubeLinkDTO addYoutubeLinkDTO) {
        if(addYoutubeLinkDTO.getYoutubeLink() == null){
            throw new MusicLibServiceException("Cannot add empty youtube link");
        }

        Song song = songService.findById(addYoutubeLinkDTO.getSongId());
        song.setYoutubeLink(addYoutubeLinkDTO.getYoutubeLink());
    }

    @Override
    public SongDTO findById(Long id) {
        return beanMappingService.mapTo(songService.findById(id), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAll() {
        return beanMappingService.mapTo(songService.findAll(), SongDTO.class);
    }

    @Override
    public List<SongDTO> findByAlbum(Long albumId) {
        return beanMappingService.mapTo(
                songService.findByAlbum(albumService.findById(albumId)), 
                SongDTO.class);
    }

    @Override
    public List<SongDTO> findByMusician(Long musicianId) {
        return beanMappingService.mapTo(
                songService.findByMusician(musicianService.findById(musicianId)), 
                SongDTO.class);
    }

    @Override
    public List<SongDTO> findByGenre(Long genreId) {
        return beanMappingService.mapTo(
                songService.findByGenre(genreService.findById(genreId)), 
                SongDTO.class);
    }
}
