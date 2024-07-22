package com.inditex.hexagonal.domain.port.in;

import com.inditex.hexagonal.rest.dto.PricesInDto;
import com.inditex.hexagonal.rest.dto.PricesOutDto;

public interface GetPricesUseCase {
    PricesOutDto getPricesInfo(PricesInDto pricesDto);
}
