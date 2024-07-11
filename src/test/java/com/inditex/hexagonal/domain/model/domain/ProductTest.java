package com.inditex.hexagonal.domain.model.domain;

import com.inditex.hexagonal.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductEntity() {
        Product product = Product.builder()
                .id(1)
                .name("Example Product")
                .build();

        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("Example Product", product.getName());
    }
}
