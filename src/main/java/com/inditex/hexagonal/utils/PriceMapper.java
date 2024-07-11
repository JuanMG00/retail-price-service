package com.inditex.hexagonal.utils;


import com.inditex.hexagonal.domain.model.Prices;
import com.inditex.hexagonal.service.dto.PricesOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "priceList.id", target = "appliedTariff")
    PricesOutDto entityToDto(Prices prices);
}
