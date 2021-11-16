package com.verly.verlyservice.application.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.verly.verlyservice.application.model.Customer;
import com.verly.verlyservice.application.model.Product;
import com.verly.verlyservice.application.repository.CustomerRepository;
import com.verly.verlyservice.application.repository.ProductRepository;
import com.verly.verlyservice.application.service.CustomerService;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }


    public void delete(Product product){
        productRepository.delete(product);
    }


}
