package com.infnet.geeklib.controller;

import com.infnet.geeklib.exception.ResourceNotFoundException;
import com.infnet.geeklib.model.Genre;
import com.infnet.geeklib.payload.MessagePayload;
import com.infnet.geeklib.repository.GenreRepository;
import com.infnet.geeklib.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;
    final GenreRepository genreRepository;

    public GenreController(GenreService genreService, GenreRepository genreRepository) {
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }

    @Operation(summary = "Lista todos os generos")
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }

    @Operation(summary = "Pega genero pela ID")
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Integer id) {
        Optional<Genre> genre = genreService.findById(id);
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo genero")
    @PostMapping
    public ResponseEntity<MessagePayload> createGenre(@RequestBody Genre genre) {
        genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

}
