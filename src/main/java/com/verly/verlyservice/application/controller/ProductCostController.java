package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.TemperedGlassCost;
import com.verly.verlyservice.application.service.TemperedGlassCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("productCost")
public class ProductCostController {
    private final TemperedGlassCostService temperedGlassCostService;

    @GetMapping
    private ResponseEntity<TemperedGlassCost> recoverCost(){
        TemperedGlassCost temperedGlassCost = temperedGlassCostService.recover();
        return ResponseEntity.ok(temperedGlassCost);
    }
}
