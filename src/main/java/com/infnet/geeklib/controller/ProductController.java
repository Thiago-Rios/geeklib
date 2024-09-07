package com.infnet.geeklib.controller;

import com.infnet.geeklib.exception.ResourceNotFoundException;
import com.infnet.geeklib.model.Product;
import com.infnet.geeklib.payload.MessagePayload;
import com.infnet.geeklib.repository.ProductRepository;
import com.infnet.geeklib.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Operation(summary = "Lista todos os produtos")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @Operation(summary = "Pega produto pela ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<MessagePayload> createProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @Operation(summary = "Atualiza um produto específico")
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            productService.update(id,product);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }

    }

    @Operation(summary = "Delata um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
