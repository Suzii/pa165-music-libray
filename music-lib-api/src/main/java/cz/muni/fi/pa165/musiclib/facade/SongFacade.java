package cz.muni.fi.pa165.musiclib.facade;

import cz.muni.fi.pa165.musiclib.dto.SongAddYoutubeLinkDTO;
import cz.muni.fi.pa165.musiclib.dto.SongDTO;
import java.util.List;

/**
 * @author zdankovc
 * @version 22/11/2015
 */
public interface SongFacade {
    /**
     * Creates new song.
     *
     * @param song entity to be created
     * @return id of newly created song
     */
    Long create(SongDTO song);
    
    void addYoutubeLink(SongAddYoutubeLinkDTO addYoutubeLinkDTO);

    /**
     * Returns the song entity attached to the given id.
     *
     * @param id id of the song entity to be returned
     * @return the song entity with given id
     */
    SongDTO findById(Long id);

    /**
     * Returns all Songs.
     *
     * @return list of all song entities
     */
    List<SongDTO> findAll();

    /**
     * Returns all songs for the given album
     *
     * @param albumId id of album to which songs belong
     * @return list of Song entities in the given album
     */
    List<SongDTO> findByAlbum(Long albumId);

    /**
     * Return all songs for the given musician
     *
     * @param musicianId id of musician to whom song belong
     * @return all Song entities for the given musician
     * @throws IllegalArgumentException if musician is null
     */
    List<SongDTO> findByMusician(Long musicianId);

    /**
     * Returns all songs for the given genre
     *
     * @param genreId id of genre to which songs belong
     * @return all Song entities for the given genre
     * @throws IllegalArgumentException if the genre is null
     */
    List<SongDTO> findByGenre(Long genreId);
}
