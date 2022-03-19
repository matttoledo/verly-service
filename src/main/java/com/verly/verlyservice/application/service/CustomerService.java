package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.customer.Address;
import com.verly.verlyservice.application.model.customer.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer create(Customer customer);

    void delete(Long id);

    Customer edit(Customer customer);
}
