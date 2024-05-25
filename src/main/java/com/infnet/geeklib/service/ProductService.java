package com.infnet.geeklib.service;

import com.infnet.geeklib.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
        return product;
    }

    public Optional<Product> update(Long id, Product product) {
        return findById(id).map(p -> {
            p.setName(product.getName());
            p.setGenre(product.getGenre());
            p.setAuthor(product.getAuthor());
            p.setDescription(product.getDescription());
            return p;
        });
    }

    public boolean delete(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
