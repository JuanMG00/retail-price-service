package com.inditex.hexagonal.service;

import com.inditex.hexagonal.service.dto.PricesInDto;
import com.inditex.hexagonal.service.dto.PricesOutDto;

public interface ProductService {
    PricesOutDto getProductInfo(PricesInDto pricesDto);
}
