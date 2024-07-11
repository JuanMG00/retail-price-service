package com.inditex.hexagonal.application.service;

import com.inditex.hexagonal.application.port.out.PricesRepository;
import com.inditex.hexagonal.domain.Prices;
import com.inditex.hexagonal.utils.ApplicationMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPricesUseCaseTest {

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private ApplicationMapper mapper;

    @InjectMocks
    private PriceServiceImpl getPricesUseCase;

    private PricesInDto pricesInDto;
    private Prices price;
    private PricesOutDto pricesOutDto;

    @BeforeEach
    public void setUp() {
        pricesInDto = new PricesInDto(1, LocalDateTime.now(), 1);
        price = new Prices();
        pricesOutDto = new PricesOutDto(1, 35455, 4,
                LocalDateTime.parse("2020-06-15T16:00"), LocalDateTime.parse("2020-12-31T23:59:59"),
                BigDecimal.valueOf(38.95).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testGetProductInfo_Success() {
        // Arrange
        when(pricesRepository.findMatchingPrice(pricesInDto.productId(), pricesInDto.brandId(),
                pricesInDto.applicationDate()))
                .thenReturn(Optional.of(price));
        when(mapper.domainToOutDto(price)).thenReturn(pricesOutDto);

        // Act
        PricesOutDto result = getPricesUseCase.getPricesInfo(pricesInDto);

        // Assert
        assertNotNull(result);
        assertEquals(pricesOutDto, result);
        verify(pricesRepository).findMatchingPrice(pricesInDto.productId(), pricesInDto.brandId(),
                pricesInDto.applicationDate());
        verify(mapper).domainToOutDto(price);
    }

    @Test
    void testGetProductInfo_Failure() {
        // Arrange
        when(pricesRepository.findMatchingPrice(pricesInDto.productId(), pricesInDto.brandId(),
                pricesInDto.applicationDate()))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                getPricesUseCase.getPricesInfo(pricesInDto));

        assertEquals(String.format("Error - No price is set for the date %s",
                pricesInDto.applicationDate()), exception.getMessage());
        verify(pricesRepository).findMatchingPrice(pricesInDto.productId(), pricesInDto.brandId(),
                pricesInDto.applicationDate());
        verify(mapper, never()).domainToOutDto(any(Prices.class));
    }
}
