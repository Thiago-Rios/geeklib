package com.infnet.geeklib;

import com.infnet.geeklib.filters.ProductFilters;
import com.infnet.geeklib.model.Genre;
import com.infnet.geeklib.model.Product;
import com.infnet.geeklib.service.GenreService;
import com.infnet.geeklib.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("Deve inserir um produto no banco")
    public void testaInsert() {
        List<Product> all = productService.getAll();
        int initState = all.size();
        Product product = new Product();
        product.setName("Livro teste");
        product.setAuthor("Autor teste");
        Genre genre = new Genre();
        genre.setName("Fantasia");
        genreService.save(genre);
        product.setGenre(genre);
        product.setDescription("Sinopse teste");
        productService.save(product);
        all = productService.getAll();
        int finalState = all.size();
        assertEquals(initState + 1, finalState);
    }

    @Test
    @DisplayName("Deve deletar um produto do banco")
    public void testaDelete() {
        Product product = new Product();
        product.setName("Livro teste");
        product.setAuthor("Autor teste");
        Genre genre = new Genre();
        genre.setName("Fantasia");
        genreService.save(genre);
        product.setGenre(genre);
        product.setDescription("Sinopse teste");
        productService.save(product);
        List<Product> all = productService.getAll();
        int initState = all.size();
        Product product2 = all.getFirst();
        productService.deleteById(product2.getId());
        all = productService.getAll();
        int finalState = all.size();
        assertEquals(initState - 1, finalState);
    }

    @Test
    @DisplayName("Deve retornar um produto no banco")
    public void testaGetById() {
        Product product = new Product();
        product.setName("Livro teste");
        product.setAuthor("Autor teste");
        Genre genre = new Genre();
        genre.setName("Fantasia");
        genreService.save(genre);
        product.setDescription("Sinopse teste");
        productService.save(product);
        List<Product> all = productService.getAll();
        Product product2 = all.getFirst();
        Optional<Product> byId = productService.findById(product2.getId());
        assertTrue(byId.isPresent());
        Optional<Product> noProduct = productService.findById(-1);
        assertTrue(noProduct.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar um produto pelo nome")
    public void testaPeloNOme() {
        List<Product> result = productService.findAllByName("Eragon");
        assertEquals(1, result.size());
        List<Product> startsWithE = productService.findAllByNameContains("E");
        assertEquals(2, startsWithE.size());
    }

    @Test
    @DisplayName("Deve buscar os produtos pelo genero Fantasia")
    public void testaPeloGenero() {
        List<Product> result = productService.findAllFantasia();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando por nome e gênero")
    public void testFindWithFiltersByNameAndGenre() {
        ProductFilters filters = ProductFilters.builder()
                .name(Optional.of("E"))
                .genre(Optional.of("Fantasia"))
                .author(Optional.empty())
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando apenas por gênero")
    public void testFindWithFiltersByGenre() {
        ProductFilters filters = ProductFilters.builder()
                .name(Optional.empty())
                .genre(Optional.of("Terror"))
                .author(Optional.empty())
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(1, result.size());
        assertEquals("Jurrassic Park", result.get(0).getName());
    }

    @Test
    @DisplayName("Deve buscar produtos filtrando por autor e gênero")
    public void testFindWithFiltersByAuthor() {
        ProductFilters filters = ProductFilters.builder()
                .name(Optional.empty())
                .genre(Optional.of("Fantasia"))
                .author(Optional.of("Christopher Paolini"))
                .build();

        List<Product> result = productService.findWithFilters(filters);
        assertEquals(2, result.size());
    }
}
