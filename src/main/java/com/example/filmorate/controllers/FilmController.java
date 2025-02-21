package com.example.filmorate.controllers;

import com.example.filmorate.models.Film;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("films")
public class FilmController {
    public static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private final Map<Integer, Film> films = new HashMap<>();

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        films.put(film.getId(), film);
        log.info("POST / film / {}", film.getName());
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("PUT / film / {}", film.getName());
        }
        return film;
    }

    @GetMapping
    public List<Film> findAllFilms() {
        return new ArrayList<>(films.values());
    }
}
