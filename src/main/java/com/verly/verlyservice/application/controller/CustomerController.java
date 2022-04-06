package com.verly.verlyservice.application.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.verly.verlyservice.application.model.customer.Customer;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    private ResponseEntity<List<Customer>> findAll(){
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path={"/{id}"})
    private ResponseEntity<Customer> findOne(@PathVariable("id") Long id){
        Customer customer = customerService.findOne(id);
//        if(Objects.isNull(customer))
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
//        log.info("getting customer: {}", customer);
        return ResponseEntity.ok(customer);
    }


    @PostMapping
    private ResponseEntity<Customer> create(@RequestBody Customer customer){
        if(Objects.isNull(customer))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        log.info("creating customer: {}", customer);
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Customer> edit(@RequestBody Customer customer, @PathVariable("id") Long id){
        if(Objects.isNull(customer))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        log.info("editing customer: {}", customer);
        customerService.edit(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
