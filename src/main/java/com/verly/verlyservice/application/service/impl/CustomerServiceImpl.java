package com.verly.verlyservice.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.verly.verlyservice.application.model.Address;
import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.ObjectWriter;

import java.util.List;
import java.util.Objects;

import javax.management.InstanceNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Gson gson = new Gson();
    private final ObjectMapper objectMapper;
    

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer, Address address) {
            var json2 = gson.toJson(address);
            log.info("json1: ", json2);
            
            return customerRepository.save(customer);

    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

}
