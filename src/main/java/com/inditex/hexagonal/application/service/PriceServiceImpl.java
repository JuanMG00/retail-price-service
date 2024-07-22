package com.inditex.hexagonal.application.service;


import com.inditex.hexagonal.domain.port.in.GetPricesUseCase;
import com.inditex.hexagonal.domain.port.out.PricesRepository;
import com.inditex.hexagonal.domain.model.Prices;
import com.inditex.hexagonal.rest.dto.PricesInDto;
import com.inditex.hexagonal.rest.dto.PricesOutDto;
import com.inditex.hexagonal.application.mappers.ApplicationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PriceServiceImpl implements GetPricesUseCase {

    private static final String NO_PRICE_SET = "Error - No price is set for the date %s";

    private final PricesRepository pricesRepository;
    private final ApplicationMapper mapper;

    @Override
    public PricesOutDto getPricesInfo(PricesInDto dto) {
        log.info("Accessing service");
        Prices price = pricesRepository.findMatchingPrice(dto.productId(), dto.brandId(), dto.applicationDate())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(NO_PRICE_SET, dto.applicationDate())));
        var mappedResponse =  mapper.domainToOutDto(price);
        log.info("Accessed service successfully");
        return mappedResponse;
    }
}
