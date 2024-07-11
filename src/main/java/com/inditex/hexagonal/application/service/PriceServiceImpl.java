package com.inditex.hexagonal.application.service;


import com.inditex.hexagonal.application.port.in.GetPricesUseCase;
import com.inditex.hexagonal.application.port.out.PricesRepository;
import com.inditex.hexagonal.domain.Prices;
import com.inditex.hexagonal.utils.ApplicationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements GetPricesUseCase {

    private static final String NO_PRICE_SET = "Error - No price is set for the date %s";

    private final PricesRepository pricesRepository;
    private final ApplicationMapper mapper;

    @Override
    public PricesOutDto getPricesInfo(PricesInDto dto) {
        Prices price = pricesRepository.findMatchingPrice(dto.productId(), dto.brandId(), dto.applicationDate())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(NO_PRICE_SET, dto.applicationDate())));
        return mapper.domainToOutDto(price);
    }
}
