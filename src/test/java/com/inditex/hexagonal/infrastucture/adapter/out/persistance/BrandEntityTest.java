package com.inditex.hexagonal.infrastucture.adapter.out.persistance;

import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.BrandEntity;
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
