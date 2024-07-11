package com.inditex.hexagonal.domain.repository;

import com.inditex.hexagonal.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesRepository extends JpaRepository<Prices, Integer> {

    @Query("SELECT p FROM Prices p " +
            "WHERE p.product.id = :productId " +
            "AND p.brand.id = :brandId " +
            "AND p.startDate <= :applicableDate " +
            "AND p.endDate >= :applicableDate " +
            "AND p.priority = (SELECT MAX(p2.priority) FROM Prices p2 " +
            "                  WHERE p2.product.id = :productId " +
            "                  AND p2.brand.id = :brandId " +
            "                  AND p2.startDate <= :applicableDate " +
            "                  AND p2.endDate >= :applicableDate)")
    Optional<Prices> findMatchingPrice(@Param("productId") Integer productId,
                                       @Param("brandId") Integer brandId,
                                       @Param("applicableDate") LocalDateTime applicableDate);
}
