package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.service.impl.CustomerServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public List<Customer> getAll();
}
