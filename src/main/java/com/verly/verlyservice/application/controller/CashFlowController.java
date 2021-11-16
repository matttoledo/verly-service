package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.CashFlow;
import com.verly.verlyservice.application.service.CashFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("cash")
public class CashFlowController {

    private final CashFlowService cashFlowService;

    @GetMapping
    private ResponseEntity<List<CashFlow>> findAll(){
        List<CashFlow> cashFlows = cashFlowService.findAll();
        return ResponseEntity.ok(cashFlows);
    }

    @PostMapping
    private ResponseEntity<CashFlow> create(@RequestBody CashFlow cash){
        return ResponseEntity.ok(cashFlowService.save(cash));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<CashFlow> edit(@RequestBody CashFlow cash, @PathVariable("id") Long id){
        return ResponseEntity.ok(cashFlowService.save(cash));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<CashFlow> delete(@RequestBody CashFlow cash, @PathVariable("id") Long id){
        cashFlowService.delete(cash);
        return ResponseEntity.ok(cash);
    }


}
