package com.inditex.hexagonal.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prices {
    private Integer id;
    private Integer brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer product;
    private BigDecimal price;
    private Integer priority;
    private String curr;
}
