package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.Order;
import com.verly.verlyservice.application.service.OrderService;
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
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    private ResponseEntity<List<Order>> findAll(){
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    private ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.ok(orderService.create(order));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Order> edit(@RequestBody Order order, @PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.edit(order));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Order> delete(@RequestBody Order order, @PathVariable("id") Long id){
        orderService.delete(order);
        return ResponseEntity.ok(order);
    }

}
