package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
}
