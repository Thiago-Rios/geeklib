package com.infnet.geeklib.service.impl;

import com.infnet.geeklib.model.Genre;
import com.infnet.geeklib.repository.GenreRepository;
import com.infnet.geeklib.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void delete(Integer id) {
        genreRepository.deleteById(id);
    }

}
