package com.inditex.hexagonal.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PricesInDto(@NotNull(message = "brandId cannot be null") Integer brandId,
                          @NotNull(message = "wrong date format") LocalDateTime applicationDate,
                          @NotNull(message = "productId cannot be null") Integer productId) {

}
