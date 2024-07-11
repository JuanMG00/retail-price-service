package com.inditex.hexagonal.infrastucture.adapter.in.web;


import com.inditex.hexagonal.application.port.in.GetPricesUseCase;
import com.inditex.hexagonal.application.service.PricesInDto;
import com.inditex.hexagonal.application.service.PricesOutDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1")
public class PricesController {

    private final GetPricesUseCase pricesService;

    @GetMapping("/prices")
    public ResponseEntity<PricesOutDto> getPrices(@Valid PricesInDto dto) {
        return ResponseEntity.ok(pricesService.getPricesInfo(dto));

    }
}
