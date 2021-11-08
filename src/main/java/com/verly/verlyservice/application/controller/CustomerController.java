package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    CustomerService customerService;

    private List<Customer> getAll(){
        return customerService.getAll();
    }


}
