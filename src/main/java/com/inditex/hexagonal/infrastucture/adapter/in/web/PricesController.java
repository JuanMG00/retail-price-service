package com.inditex.hexagonal.infrastucture.adapter.in.web;


import com.inditex.hexagonal.application.port.in.GetPricesUseCase;
import com.inditex.hexagonal.application.service.PricesInDto;
import com.inditex.hexagonal.application.service.PricesOutDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
@Log4j2
public class PricesController {

    private final GetPricesUseCase pricesService;

    @GetMapping("/prices")
    public ResponseEntity<PricesOutDto> getPrices(
            @Parameter(description = "Brand ID", example = "1")
            @RequestParam @NotNull(message = "brandId cannot be null") Integer brandId,
            @Parameter(description = "Application Date", example = "2020-06-14 00:00:01")
            @RequestParam @NotNull(message = "wrong date format")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
            @Parameter(description = "Product ID", example = "35455")
            @RequestParam @NotNull(message = "productId cannot be null") Integer productId) {
        PricesInDto dto = new PricesInDto(brandId, applicationDate, productId);
        log.info("Accessing prices controller");
        var res = pricesService.getPricesInfo(dto);
        log.info("Processed controller successfully");
        return ResponseEntity.ok(res);
    }
}
