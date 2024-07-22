package com.inditex.hexagonal.infrastucture.persistance.repository;


import com.inditex.hexagonal.domain.model.Prices;
import com.inditex.hexagonal.domain.port.out.PricesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
class PricesEntityRepositoryIT {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void testDatabaseRepository_test() {
        var responseList = pricesRepository.findAll();
        Assertions.assertEquals(4, responseList.size());
    }

    @Test
    void findMatchingPrice_test() {
        var date = LocalDateTime.parse("2020-06-14T10:00:00");
        var response = pricesRepository.findMatchingPrice(35455, 1, date);
        //noinspection OptionalGetWithoutIsPresent
        Prices prices = response.get();
        Assertions.assertEquals("EUR", prices.getCurr());
    }
}
