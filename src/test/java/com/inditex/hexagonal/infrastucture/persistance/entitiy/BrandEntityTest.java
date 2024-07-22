package com.inditex.hexagonal.infrastucture.persistance.entitiy;

import com.inditex.hexagonal.infrastucture.persistance.entity.BrandEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BrandEntityTest {

    @Test
    void testBrandEntity() {
        BrandEntity brand = BrandEntity.builder()
                .id(1)
                .name("Example Brand")
                .build();

        Assertions.assertEquals(1, brand.getId());
        Assertions.assertEquals("Example Brand", brand.getName());
    }
}
