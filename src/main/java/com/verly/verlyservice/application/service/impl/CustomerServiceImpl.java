package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.customer.Customer;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return customerRepository.save(customer);

    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer edit(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findById(id).get();
    }

}
