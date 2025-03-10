package com.example.filmorate.storage;

import com.example.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    Film create(Film film);

    Film update(Film film);

    List<Film> findAllFilms();

    Film getFilmById(int id);

}
