package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    private ResponseEntity findAll(){
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    private ResponseEntity create(@RequestBody Customer customer){
        customerService.save(customer);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    private ResponseEntity edit(@RequestBody Customer customer, @PathVariable("id") Long id){
        customerService.save(customer);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


}
