package com.inditex.hexagonal.application.port.in;

import com.inditex.hexagonal.application.service.PricesInDto;
import com.inditex.hexagonal.application.service.PricesOutDto;

public interface GetPricesUseCase {
    PricesOutDto getPricesInfo(PricesInDto pricesDto);
}
