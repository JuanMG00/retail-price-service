package com.inditex.hexagonal.domain.repository;


import com.inditex.hexagonal.application.port.out.PricesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
class PricesEntityRepositoryIT {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void testDatabaseRepository() {
        var responseList = pricesRepository.findAll();
        Assertions.assertEquals(4, responseList.size());
    }
}
