package com.infnet.geeklib.controller;

import com.infnet.geeklib.model.ProductLog;
import com.infnet.geeklib.service.ProductLogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-logs")
public class ProductLogController {

    private final ProductLogService productLogService;

    public ProductLogController(ProductLogService productLogService) {
        this.productLogService = productLogService;
    }

    @Operation(summary = "Lista todos os logs")
    @GetMapping
    public ResponseEntity<List<ProductLog>> getAllLogs() {
        List<ProductLog> logs = productLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }
}
