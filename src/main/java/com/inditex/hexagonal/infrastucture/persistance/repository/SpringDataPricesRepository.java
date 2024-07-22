package com.inditex.hexagonal.infrastucture.persistance.repository;

import com.inditex.hexagonal.infrastucture.persistance.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataPricesRepository extends JpaRepository<PricesEntity, Integer> {
    @Query("SELECT p FROM PricesEntity p " +
            "WHERE p.product.id = :productId " +
            "AND p.brand.id = :brandId " +
            "AND p.startDate <= :applicableDate " +
            "AND p.endDate >= :applicableDate " +
            "AND p.priority = (SELECT MAX(p2.priority) FROM PricesEntity p2 " +
            "                  WHERE p2.product.id = :productId " +
            "                  AND p2.brand.id = :brandId " +
            "                  AND p2.startDate <= :applicableDate " +
            "                  AND p2.endDate >= :applicableDate)")
    Optional<PricesEntity> findMatchingPrice(@Param("productId") Integer productId,
                                             @Param("brandId") Integer brandId,
                                             @Param("applicableDate") LocalDateTime applicableDate);
}
