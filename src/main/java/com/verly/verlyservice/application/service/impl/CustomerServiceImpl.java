package com.verly.verlyservice.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.verly.verlyservice.application.model.customer.Address;
import com.verly.verlyservice.application.model.customer.Customer;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.ObjectWriter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.management.InstanceNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
            customer.setCreatedAt(LocalDateTime.now());
            // Address addressJson = gson.fromJson(customer.getAddress(), Address.class);
            
            log.info("json: ", customer);
            
            return customerRepository.save(customer);

    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }


}
