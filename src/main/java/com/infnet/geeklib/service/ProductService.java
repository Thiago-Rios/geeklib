package com.infnet.geeklib.service;

import com.infnet.geeklib.filters.ProductFilters;
import com.infnet.geeklib.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Optional<Product> findById(Integer id);
    void deleteById(Integer id);
    void save(Product product);
    Product update(Integer id, Product product);
    List<Product> findAllByName(String name);
    List<Product> findAllByNameContains(String name);
    List<Product> findAllFantasia();

    List<Product> findWithFilters(ProductFilters filters);
}
