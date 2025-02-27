package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.service.FilmService;
import com.example.filmorate.storage.FilmStorage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmStorage filmStorage;
    private final FilmService filmService;
    public static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    public FilmController(FilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    @PostMapping("/create")
    public Film create(@Valid @RequestBody Film film) {
        log.info("POST / film / {}", film.getName());
        return filmStorage.create(film);
    }

    @PutMapping("/update")
    public Film update(@Valid @RequestBody Film film) {
        log.info("PUT / film / {}", film.getName());
        return filmStorage.update(film);
    }

    @GetMapping
    public List<Film> findAllFilms() {
        return filmStorage.findAllFilms();
    }
}
