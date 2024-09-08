package com.infnet.geeklib.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
public class ProductLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Integer product_id;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String product_author;

    @Column(nullable = false)
    private String product_description;
}
