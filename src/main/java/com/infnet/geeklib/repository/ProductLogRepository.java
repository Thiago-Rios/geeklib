package com.infnet.geeklib.repository;

import com.infnet.geeklib.model.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Integer> {
}
