package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer save(Customer customer);

    void delete(Customer customer);
}