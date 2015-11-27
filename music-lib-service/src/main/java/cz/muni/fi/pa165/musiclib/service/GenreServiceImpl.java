package cz.muni.fi.pa165.musiclib.service;

import cz.muni.fi.pa165.musiclib.dao.GenreDao;
import cz.muni.fi.pa165.musiclib.entity.Genre;
import cz.muni.fi.pa165.musiclib.exception.MusicLibDataAccessException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author David Boron
 */
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDao;

    @Override
    public void create(Genre genre) {
        try {
            genreDao.create(genre);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            throw new MusicLibDataAccessException("genre create error", e);
        }
    }

    @Override
    public Genre update(Genre genre) {
        try {
            return genreDao.update(genre);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new MusicLibDataAccessException("genre update error", e);
        }
    }

    @Override
    public void remove(Genre genre) {
        try {
            genreDao.remove(genre);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new MusicLibDataAccessException("genre remove error", e);
        }
    }

    @Override
    public Genre findById(Long id) {
        try {
            return genreDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MusicLibDataAccessException("genre findById error", e);
        }
    }

    @Override
    public List<Genre> findByTitle(String title) {
        try {
            return genreDao.findByTitle(title);
        } catch (IllegalArgumentException e) {
            throw new MusicLibDataAccessException("genre findByTitle error", e);
        }
    }

    @Override
    public List<Genre> findAll() {
        try {
            return genreDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new MusicLibDataAccessException("genre findAll error", e);
        }
    }

    @Override
    public void changeTitle(Genre genre, String title) {
        try {
            genre.setTitle(title);
        } catch (Exception e) {
            throw new MusicLibDataAccessException("genre changeTitle error", e);
        }
    }

}
