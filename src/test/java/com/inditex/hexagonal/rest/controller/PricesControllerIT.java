package com.inditex.hexagonal.rest.controller;


import com.inditex.hexagonal.rest.dto.PricesOutDto;
import com.inditex.hexagonal.rest.exception.RestExceptionHandler;
import com.inditex.hexagonal.infrastucture.persistance.entity.BrandEntity;
import com.inditex.hexagonal.infrastucture.persistance.entity.PriceListEntity;
import com.inditex.hexagonal.infrastucture.persistance.entity.PricesEntity;
import com.inditex.hexagonal.infrastucture.persistance.entity.ProductEntity;
import com.inditex.hexagonal.infrastucture.persistance.entity.enums.Currency;
import com.inditex.hexagonal.infrastucture.persistance.repository.SpringDataPricesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-it.properties")
class PricesControllerIT {


    @SuppressWarnings("unused")
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SpringDataPricesRepository springDataPricesRepository;

    PricesOutDto expectedPriceList1 =
            new PricesOutDto(1, 35455, 1,
                    LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"),
                    BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP));


    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testDateRangesCase1_10_00_14() {
        String date = "2020-06-14 10:00:00";

        PricesOutDto response = buildOkRequest(date);
        Assertions.assertEquals(expectedPriceList1, response);
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testDateRangesCase2_16_00_14() {
        String date = "2020-06-14 16:00:00";

        PricesOutDto expectedResult =
                new PricesOutDto(1, 35455, 2,
                        LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"),
                        BigDecimal.valueOf(25.45).setScale(2, RoundingMode.HALF_UP));
        PricesOutDto response = buildOkRequest(date);
        Assertions.assertEquals(expectedResult, response);
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testDateRangesCase3_21_00_14() {
        String date = "2020-06-14 21:00:00";

        PricesOutDto response = buildOkRequest(date);
        Assertions.assertEquals(expectedPriceList1, response);
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testDateRangesCase4_10_00_15() {
        String date = "2020-06-15 10:00:00";
        PricesOutDto expectedResult =
                new PricesOutDto(1, 35455, 3,
                        LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"),
                        BigDecimal.valueOf(30.5).setScale(2, RoundingMode.HALF_UP));
        PricesOutDto response = buildOkRequest(date);
        Assertions.assertEquals(expectedResult, response);
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testDateRangesCase5_21_00_16() {
        String date = "2020-06-16 21:00:00";
        PricesOutDto expectedResult =
                new PricesOutDto(1, 35455, 4,
                        LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"),
                        BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP));
        PricesOutDto response = buildOkRequest(date);
        Assertions.assertEquals(expectedResult, response);
    }

    @Test
    void testHighestPriority() {

        // set start date and end date with same value for all prices
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        // persist 3 different prices with different prices in the same period with different priorities
        PricesEntity prices1 = springDataPricesRepository.save(buildPrices(0, startDate, endDate, BigDecimal.valueOf(5)));
        PricesEntity prices2 = springDataPricesRepository.save(buildPrices(1, startDate, endDate, BigDecimal.valueOf(10)));
        PricesEntity prices3 = springDataPricesRepository.save(buildPrices(2, startDate, endDate, BigDecimal.valueOf(15)));

        // format date so that request understands it
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateRequest = startDate.plusHours(1).format(formatter);

        PricesOutDto response = buildOkRequest(dateRequest);
        // check that the prices response is with the highest priority
        Assertions.assertEquals(prices3.getPrice().setScale(2, RoundingMode.HALF_UP), response.price());

        //modify priority of price 1 to highest
        prices1.setPriority(3);
        springDataPricesRepository.save(prices1);

        PricesOutDto response2 = buildOkRequest(dateRequest);
        // check that the prices response is with the highest priority
        Assertions.assertEquals(prices1.getPrice().setScale(2, RoundingMode.HALF_UP), response2.price());

        //modify priority of price 2 to highest
        prices2.setPriority(4);
        springDataPricesRepository.save(prices2);

        PricesOutDto response3 = buildOkRequest(dateRequest);
        // check that the prices response is with the highest priority
        Assertions.assertEquals(prices2.getPrice().setScale(2, RoundingMode.HALF_UP), response3.price());

        springDataPricesRepository.delete(prices1);
        springDataPricesRepository.delete(prices2);
        springDataPricesRepository.delete(prices3);

    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void testControllerInputs() {
        String date = "2020-06-16 21:00:00";
        // request without product id
        ResponseEntity<RestExceptionHandler.ErrorResponse> r = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?applicationDate=%s&brandId=1", port, date),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals("Required request parameter 'productId' for " +
                "method parameter type Integer is not present", Objects.requireNonNull(r.getBody()).message());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());


        // request without brand id
        ResponseEntity<RestExceptionHandler.ErrorResponse> r1 = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?applicationDate=%s&productId=35455", port, date),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals("Required request parameter 'brandId' for method parameter type " +
                "Integer is not present", Objects.requireNonNull(r1.getBody()).message());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r1.getStatusCode());

        // request without applicationDate
        ResponseEntity<RestExceptionHandler.ErrorResponse> r2 = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?brandId=1&productId=35455", port),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals("Required request parameter 'applicationDate' " +
                "for method parameter type LocalDateTime is not present", Objects.requireNonNull(r2.getBody()).message());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r2.getStatusCode());

        // request witho wrong applicationDate format
        ResponseEntity<RestExceptionHandler.ErrorResponse> r5 = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?applicationDate=%s" +
                        "&brandId=1&productId=35455", port, date + "abc"),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals("Failed to convert value of type 'java.lang.String' to required type " +
                "'java.time.LocalDateTime'; Failed to convert from type [java.lang.String] to type " +
                "[@io.swagger.v3.oas.annotations.Parameter @org.springframework.web.bind.annotation.RequestParam " +
                "@jakarta.validation.constraints.NotNull " +
                "@org.springframework.format.annotation.DateTimeFormat java.time.LocalDateTime] " +
                "for value [2020-06-16 21:00:00abc]", Objects.requireNonNull(r5.getBody()).message());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r5.getStatusCode());

        // request without parameters
        ResponseEntity<RestExceptionHandler.ErrorResponse> r3 = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices", port),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r3.getStatusCode());

        // request with no result
        ResponseEntity<RestExceptionHandler.ErrorResponse> r4 = restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?applicationDate=%s&brandId=1&productId=354551", port, date),
                RestExceptionHandler.ErrorResponse.class);
        Assertions.assertEquals("Error - No price is set for the date 2020-06-16T21:00",
                Objects.requireNonNull(r4.getBody()).message());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());

    }


    /**
     * Builds and calls request to retrieve PricesOutDto using the specified date.
     *
     * @param date The date parameter for the request.
     * @return The PricesOutDto object obtained from the request.
     */
    private PricesOutDto buildOkRequest(String date) {
        ResponseEntity<PricesOutDto> response = buildRequest(date);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        return response.getBody();
    }

    private ResponseEntity<PricesOutDto> buildRequest(String date) {
        return restTemplate.getForEntity(
                String.format("http://localhost:%s/v1/prices?productId=35455&applicationDate=%s&brandId=1", port, date),
                PricesOutDto.class);
    }


    private PricesEntity buildPrices(Integer priority, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        return PricesEntity.builder()
                .brand(BrandEntity.builder().id(1).build())
                .priority(priority)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .priceList(PriceListEntity.builder().id(1).build())
                .product(ProductEntity.builder().id(35455).build())
                .curr(Currency.EUR)
                .build();
    }
}
