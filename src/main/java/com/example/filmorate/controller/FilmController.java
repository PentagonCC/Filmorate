package com.example.filmorate.controller;

import com.example.filmorate.model.Film;
import com.example.filmorate.service.FilmService;
import com.example.filmorate.storage.FilmStorage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

    @GetMapping("/{id}")
    public Film getFilmByID(@PathVariable String id) {
        log.info("GET / films / id / {}", id);
        return filmStorage.getFilmById(Integer.parseInt(id));
    }

    @PutMapping("/{id}/like/{userId}")
    public void likeFilm(@PathVariable String id, @PathVariable String userId) {
        log.info("PUT / films / id / like / userId / {}", id);
        filmService.likeFilm(Integer.parseInt(id), Integer.parseInt(userId));
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable String id, @PathVariable String userId) {
        log.info("DELETE / films / id / like / userId / {}", id);
        filmService.deleteLikeFilm(Integer.parseInt(id), Integer.parseInt(userId));
    }

    @GetMapping("/popular")
    public List<Film> getTopFilms(@RequestParam(defaultValue = "10") String count){
        log.info("GET / films / popular / {}");
        return filmService.getTopFilms(Integer.parseInt(count));
    }
}
