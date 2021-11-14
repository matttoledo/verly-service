package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer save(Customer customer){
        log.debug("saving "+customer.toString());
        return customerRepository.save(customer);
    }


    public void delete(Customer customer){
        customerRepository.delete(customer);
    }


}