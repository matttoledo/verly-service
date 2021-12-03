package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.customer.Address;
import com.verly.verlyservice.application.model.customer.Customer;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;

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

    @PostMapping
    private ResponseEntity<Customer> create(@RequestBody Customer customer){
        if(Objects.isNull(customer.getName())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        customer.setDefaulter(false);
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Customer> edit(@RequestBody Customer customer, @PathVariable("id") Long id){
        customerService.create(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Customer> delete(@RequestBody Customer customer, @PathVariable("id") Long id){
        customerService.delete(customer);
        return ResponseEntity.ok(customer);
    }


}
