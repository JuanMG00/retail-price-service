package com.inditex.hexagonal.infrastucture.adapter.out.persistance.repository;

import com.inditex.hexagonal.application.port.out.PricesRepository;
import com.inditex.hexagonal.domain.Prices;
import com.inditex.hexagonal.utils.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PricesRepositoryAdapter implements PricesRepository {
    private final SpringDataPricesRepository springDataPricesRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public Optional<Prices> findMatchingPrice(Integer productId, Integer brandId, LocalDateTime applicableDate) {
        var pEntityOptional = springDataPricesRepository.findMatchingPrice(productId, brandId, applicableDate);
        return applicationMapper.optionalEntityToOptionalDomain(pEntityOptional);

    }

    @Override
    public List<Prices> findAll() {
        return springDataPricesRepository.findAll().stream().map(applicationMapper::entityToDomain).toList();
    }
}
