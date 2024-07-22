package com.inditex.hexagonal.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PricesTest {

    @Test
    @DisplayName("Test domain Prices no arguments constructor ")
    void testNoArgsConstructor() {
        Prices prices = new Prices();

        Assertions.assertThat(prices.getId()).isNull();
        Assertions.assertThat(prices.getBrand()).isNull();
        Assertions.assertThat(prices.getStartDate()).isNull();
        Assertions.assertThat(prices.getEndDate()).isNull();
        Assertions.assertThat(prices.getPriceList()).isNull();
        Assertions.assertThat(prices.getProduct()).isNull();
        Assertions.assertThat(prices.getPrice()).isNull();
        Assertions.assertThat(prices.getPriority()).isNull();
        Assertions.assertThat(prices.getCurr()).isNull();
    }

    @Test
    @DisplayName("Test domain Prices all arguments constructor ")
    void testAllArgsConstructor() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        BigDecimal priceValue = new BigDecimal("29.99");

        Prices prices = new Prices(1, 2, startDate, endDate, 3, 4, priceValue, 5, "EUR");

        Assertions.assertThat(prices.getId()).isEqualTo(1);
        Assertions.assertThat(prices.getBrand()).isEqualTo(2);
        Assertions.assertThat(prices.getStartDate()).isEqualTo(startDate);
        Assertions.assertThat(prices.getEndDate()).isEqualTo(endDate);
        Assertions.assertThat(prices.getPriceList()).isEqualTo(3);
        Assertions.assertThat(prices.getProduct()).isEqualTo(4);
        Assertions.assertThat(prices.getPrice()).isEqualTo(priceValue);
        Assertions.assertThat(prices.getPriority()).isEqualTo(5);
        Assertions.assertThat(prices.getCurr()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Test domain Prices builder ")
    void testBuilder() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        BigDecimal priceValue = new BigDecimal("29.99");

        Prices prices = Prices.builder()
                .id(1)
                .brand(2)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(3)
                .product(4)
                .price(priceValue)
                .priority(5)
                .curr("EUR")
                .build();

        Assertions.assertThat(prices.getId()).isEqualTo(1);
        Assertions.assertThat(prices.getBrand()).isEqualTo(2);
        Assertions.assertThat(prices.getStartDate()).isEqualTo(startDate);
        Assertions.assertThat(prices.getEndDate()).isEqualTo(endDate);
        Assertions.assertThat(prices.getPriceList()).isEqualTo(3);
        Assertions.assertThat(prices.getProduct()).isEqualTo(4);
        Assertions.assertThat(prices.getPrice()).isEqualTo(priceValue);
        Assertions.assertThat(prices.getPriority()).isEqualTo(5);
        Assertions.assertThat(prices.getCurr()).isEqualTo("EUR");
    }
}
