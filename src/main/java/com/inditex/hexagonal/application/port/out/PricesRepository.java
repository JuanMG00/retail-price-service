package com.inditex.hexagonal.application.port.out;

import com.inditex.hexagonal.domain.Prices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PricesRepository {

    Optional<Prices> findMatchingPrice(Integer productId, Integer brandId, LocalDateTime applicableDate);

    List<Prices> findAll();
}
