package com.inditex.hexagonal.infrastucture.persistance.entity;

import com.inditex.hexagonal.infrastucture.persistance.entity.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRICES")
public class PricesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @JoinColumn
    @NotNull
    @ManyToOne
    private BrandEntity brand;

    @Column
    @NotNull
    private LocalDateTime startDate;
    @Column
    @NotNull
    private LocalDateTime endDate;

    @JoinColumn(name = "price_list")
    @NotNull
    @ManyToOne
    private PriceListEntity priceList;

    @JoinColumn
    @NotNull
    @ManyToOne
    private ProductEntity product;

    @Column
    @NotNull
    private BigDecimal price;

    @Column
    @NotNull
    private Integer priority;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency curr;
}
