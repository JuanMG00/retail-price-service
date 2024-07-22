package com.inditex.hexagonal.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Output data for prices")
public record PricesOutDto(
        @Schema(description = "Brand ID", example = "1") Integer brandId,
        @Schema(description = "Product ID", example = "35455") Integer productId,
        @Schema(description = "Applied Tariff", example = "2") Integer appliedTariff,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Schema(description = "Start Date", example = "2020-06-14 00:00:00") LocalDateTime startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Schema(description = "End Date", example = "2020-12-31 23:59:59") LocalDateTime endDate,
        @Schema(description = "Price", example = "29.99") BigDecimal price) {
}
