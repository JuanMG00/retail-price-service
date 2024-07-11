package com.inditex.hexagonal.infrastucture.adapter.out.persistance.repository;

import com.inditex.hexagonal.application.port.out.PricesRepository;
import com.inditex.hexagonal.domain.Prices;
import com.inditex.hexagonal.utils.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class PricesRepositoryAdapter implements PricesRepository {
    private final SpringDataPricesRepository springDataPricesRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public Optional<Prices> findMatchingPrice(Integer productId, Integer brandId, LocalDateTime applicableDate) {
        log.info("Entering repository -> findMatchingPrice");
        var pEntityOptional = springDataPricesRepository.findMatchingPrice(productId, brandId, applicableDate);
        var response = applicationMapper.optionalEntityToOptionalDomain(pEntityOptional);
        log.info("Entered repository successfully -> findMatchingPrice");
        return response;
    }

    @Override
    public List<Prices> findAll() {
        log.info("Entering repository -> findAll");
        var response = springDataPricesRepository.findAll().stream().map(applicationMapper::entityToDomain).toList();
        log.info("Entered repository successfully -> findAll");
        return response;
    }
}
