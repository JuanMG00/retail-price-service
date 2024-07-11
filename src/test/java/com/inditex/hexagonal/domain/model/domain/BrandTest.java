package com.inditex.hexagonal.domain.model.domain;

import com.inditex.hexagonal.domain.model.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BrandTest {

    @Test
    void testBrandEntity() {
        Brand brand = Brand.builder()
                .id(1)
                .name("Example Brand")
                .build();

        Assertions.assertEquals(1, brand.getId());
        Assertions.assertEquals("Example Brand", brand.getName());
    }
}
