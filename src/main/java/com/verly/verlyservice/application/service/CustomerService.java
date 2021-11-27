package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Address;
import com.verly.verlyservice.application.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer create(Customer customer, String address);

    void delete(Customer customer);
}
