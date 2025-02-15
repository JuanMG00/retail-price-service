package com.inditex.hexagonal.application.service;

import com.inditex.hexagonal.domain.port.in.GetPricesUseCase;
import com.inditex.hexagonal.rest.dto.PricesInDto;
import com.inditex.hexagonal.rest.dto.PricesOutDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
class PricesServiceImplIT {


    @Autowired
    private GetPricesUseCase getPricesUseCase;

    @Test
    @DisplayName("get prices info successfully")
    void testGetPricesInfo_ok() {
        PricesInDto in = new PricesInDto(1, LocalDateTime.parse("2020-12-31T23:59:59"), 35455);
        PricesOutDto expectedResult = new PricesOutDto(1, 35455, 4,
                LocalDateTime.parse("2020-06-15T16:00"), LocalDateTime.parse("2020-12-31T23:59:59"),
                BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP));
        Assertions.assertEquals(expectedResult, getPricesUseCase.getPricesInfo(in));
    }

    @Test
    @DisplayName("don't find a prices")
    void testGetPricesInfo_notFound() {
        PricesInDto in = new PricesInDto(1, LocalDateTime.parse("2024-12-31T23:59:59"), 35455);
        assertThrows(EntityNotFoundException.class, () -> getPricesUseCase.getPricesInfo(in));
    }


}
