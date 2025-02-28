package com.example.filmorate.service;

import com.example.filmorate.model.Film;
import com.example.filmorate.storage.FilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    public final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void likeFilm(int filmId, int userId) {
        Film film = filmStorage.getFilmById(filmId);
        if (film != null) {
            if(!film.getLikes().contains((Integer) userId)) {
                film.getLikes().add(userId);
            }
        }
    }

    public void deleteLikeFilm(int filmId, int userId) {
        Film film = filmStorage.getFilmById(filmId);
        if(film != null){
            if(film.getLikes().contains((Integer) userId)){
                film.getLikes().remove(userId);
            }
        }
    }

    public List<Film> getTopFilms(int count) {
        return filmStorage.findAllFilms().stream().sorted((film1, film2) ->
                film2.getLikes().size() - film1.getLikes().size()).limit(count).collect(Collectors.toList());
    }


}
