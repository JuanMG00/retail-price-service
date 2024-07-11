package com.inditex.hexagonal.infrastucture.adapter.out.persistance;

import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.BrandEntity;
import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.PriceListEntity;
import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.PricesEntity;
import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.ProductEntity;
import com.inditex.hexagonal.infrastucture.adapter.out.persistance.entity.enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PricesEntityTest {

    @Test
    void testPricesEntity() {

        BrandEntity brand = BrandEntity.builder().id(1).name("Example Brand").build();
        PriceListEntity priceList = PriceListEntity.builder().id(1).build();
        ProductEntity product = ProductEntity.builder().id(1).name("Example Product").build();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(7);

        PricesEntity prices = PricesEntity.builder()
                .id(1)
                .brand(brand)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .product(product)
                .price(BigDecimal.valueOf(9.99))
                .priority(1)
                .curr(Currency.EUR)
                .build();

        Assertions.assertEquals(1, prices.getId());
        Assertions.assertEquals(brand, prices.getBrand());
        Assertions.assertEquals(startDate, prices.getStartDate());
        Assertions.assertEquals(endDate, prices.getEndDate());
        Assertions.assertEquals(priceList, prices.getPriceList());
        Assertions.assertEquals(product, prices.getProduct());
        Assertions.assertEquals(BigDecimal.valueOf(9.99), prices.getPrice());
        Assertions.assertEquals(1, prices.getPriority());
        Assertions.assertEquals(Currency.EUR, prices.getCurr());
    }
}
