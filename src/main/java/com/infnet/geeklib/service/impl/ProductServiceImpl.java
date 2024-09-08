package com.infnet.geeklib.service.impl;

import com.infnet.geeklib.filters.ProductFilters;
import com.infnet.geeklib.model.Genre;
import com.infnet.geeklib.model.Product;
import com.infnet.geeklib.model.ProductLog;
import com.infnet.geeklib.repository.ProductLogRepository;
import com.infnet.geeklib.repository.ProductRepository;
import com.infnet.geeklib.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductLogRepository productLogRepository;
    private final EntityManager entityManager;


    @Override
    public List<Product> findWithFilters(ProductFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getName().isPresent()) {
            String query = filters.getName().get() + "%";
            Predicate name = cb.like(productRoot.get("name"), query);
            predicates.add(name);
        }
        if (filters.getGenre().isPresent()) {
            Join<Product, Genre> genreJoin = productRoot.join("genre");
            Predicate genre = cb.equal(genreJoin.get("name"), filters.getGenre().get());
            predicates.add(genre);
        }
        if (filters.getAuthor().isPresent()) {
            Predicate author = cb.equal(productRoot.get("author"), filters.getAuthor().get());
            predicates.add(author);
        }
        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(array);
        List<Product> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(prod -> {
            saveLog(prod, "DELETE");
            productRepository.deleteById(id);
        });
    }

    @Override
    public void save(Product product) {
        Product savedProduct = productRepository.save(product);
        saveLog(savedProduct, "CREATE");
    }

    @Override
    public Product update(Integer id, Product product) {
        product.setId(id);
        Product upgradedProduct = productRepository.save(product);
        saveLog(upgradedProduct, "UPDATE");
        return upgradedProduct;
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findAllByNameContains(String name) {
        return productRepository.findAllByNameContains(name);
    }

    @Override
    public List<Product> findAllFantasia() {
        return productRepository.findAllFantasia();
    }

    private void saveLog(Product product, String operation) {
        ProductLog log = new ProductLog();
        log.setProduct_id(product.getId());
        log.setProduct_name(product.getName());
        log.setProduct_author(product.getAuthor());
        log.setProduct_description(product.getDescription());
        log.setOperation(operation);
        log.setTimestamp(LocalDateTime.now());
        productLogRepository.save(log);
    }
}
