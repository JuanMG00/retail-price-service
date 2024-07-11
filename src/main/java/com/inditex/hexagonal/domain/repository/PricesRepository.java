package com.inditex.hexagonal.domain.repository;

import com.inditex.hexagonal.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Prices, Integer> {

}
