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
    private ResponseEntity<List<ProductCost>> findAll(){
        List<ProductCost> productCost = productCostService.findAll();
        return ResponseEntity.ok(productCost);
    }

    @PostMapping
    private ResponseEntity<ProductCost> create(@RequestBody ProductCost productCost){
        if(Objects.isNull(productCost))
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productCostService.create(productCost));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ProductCost> edit(@RequestBody ProductCost productCost, @PathVariable("id") Long id){
        return ResponseEntity.ok(productCostService.edit(productCost));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ProductCost> delete(@RequestBody ProductCost productCost, @PathVariable("id") Long id){
        return ResponseEntity.ok(productCost);
    }
}
