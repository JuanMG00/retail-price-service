package com.inditex.hexagonal.infrastucture.persistance.entitiy;

import com.inditex.hexagonal.infrastucture.persistance.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductEntityTest {

    @Test
    void testProductEntity() {
        ProductEntity product = ProductEntity.builder()
                .id(1)
                .name("Example Product")
                .build();

        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("Example Product", product.getName());
    }
}
