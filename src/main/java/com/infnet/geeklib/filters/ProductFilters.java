package com.infnet.geeklib.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor@NoArgsConstructor@Builder
@Data
public class ProductFilters {
    private Optional<String> name;
    private Optional<String> author;
    private Optional<String> genre;
}
