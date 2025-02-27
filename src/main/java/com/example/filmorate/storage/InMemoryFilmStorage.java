package com.example.filmorate.storage;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.model.Film;
import com.example.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> films = new HashMap<>();

    @Override
    public Film create(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
        }
        return film;
    }

    @Override
    public List<Film> findAllFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(int id){
        if (films.containsKey(id)) {
            return films.get(id);
        }else throw new NotFoundException("Film not found");
    }
}
