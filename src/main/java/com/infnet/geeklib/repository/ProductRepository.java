package com.infnet.geeklib.repository;

import com.infnet.geeklib.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name);
    List<Product> findAllByNameContains(String name);

    @Query("SELECT p FROM Product p INNER JOIN p.genre g WHERE g.id = 1")
    List<Product> findAllFantasia();
}
