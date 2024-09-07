package com.infnet.geeklib.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @NotBlank
    private String author;
    @NotBlank
    private String description;

}
