package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.ProductCost;
import com.verly.verlyservice.application.repository.ProductCostRepository;
import com.verly.verlyservice.application.service.ProductCostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class ProductCostServiceImpl implements ProductCostService {

    private final ProductCostRepository productCostRepository;

    @Override
    public List<ProductCost> findAll() {
        return productCostRepository.findAll();
    }

    @Override
    public ProductCost create(ProductCost productCost) {
        return productCostRepository.save(productCost);
    }

    @Override
    public ProductCost edit(ProductCost productCost) {
        return productCostRepository.save(productCost);
    }

    @Override
    public void delete(Long id){
        productCostRepository.deleteById(id);
    }

    @Override
    public ProductCost findOne(Long id) {
        return productCostRepository.findById(id).get();
    }


}
