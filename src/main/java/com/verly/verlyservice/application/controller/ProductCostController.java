package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.ProductCost;
import com.verly.verlyservice.application.service.ProductCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("productCost")
public class ProductCostController {
    private final ProductCostService productCostService;

    @GetMapping
    private ResponseEntity<ProductCost> recoverCost(){
        ProductCost productCost = productCostService.recoverCost();
        return ResponseEntity.ok(productCost);
    }
}
