package com.infnet.geeklib;

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
}
