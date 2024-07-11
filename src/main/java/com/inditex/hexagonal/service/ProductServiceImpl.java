package com.inditex.hexagonal.service;


import com.inditex.hexagonal.domain.model.Prices;
import com.inditex.hexagonal.domain.repository.PricesRepository;
import com.inditex.hexagonal.service.dto.PricesInDto;
import com.inditex.hexagonal.service.dto.PricesOutDto;
import com.inditex.hexagonal.utils.PriceMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String NO_PRICE_SET = "Error - No price is set for the date %s";

    private final PricesRepository pricesRepository;
    private final PriceMapper mapper;

    @Override
    public PricesOutDto getProductInfo(PricesInDto dto) {
        Prices price = pricesRepository.findMatchingPrice(dto.productId(), dto.brandId(), dto.applicationDate())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(NO_PRICE_SET, dto.applicationDate())));
        return mapper.entityToDto(price);
    }
}
