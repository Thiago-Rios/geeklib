package com.infnet.geeklib.service;

import com.infnet.geeklib.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Integer id);
    void save(Genre genre);
    void delete(Integer id);
    List<Genre> findAll();
}
