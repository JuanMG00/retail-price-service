package com.inditex.hexagonal.application.mappers;


import com.inditex.hexagonal.rest.dto.PricesOutDto;
import com.inditex.hexagonal.domain.model.Prices;
import com.inditex.hexagonal.infrastucture.persistance.entity.PricesEntity;
import com.inditex.hexagonal.infrastucture.persistance.entity.enums.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "priceList", target = "appliedTariff")
    @Mapping(source = "brand", target = "brandId")
    @Mapping(source = "product", target = "productId")
    PricesOutDto domainToOutDto(Prices prices);

    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "product.id", target = "product")
    @Mapping(source = "priceList.id", target = "priceList")
    @Mapping(source = "curr", target = "curr")
    Prices entityToDomain(PricesEntity entity);

    default String mapCurr(Currency currency) {
        return currency == null ? null : currency.name();
    }

    default Optional<Prices> optionalEntityToOptionalDomain(Optional<PricesEntity> entity) {
        return entity.map(this::entityToDomain);
    }

}
