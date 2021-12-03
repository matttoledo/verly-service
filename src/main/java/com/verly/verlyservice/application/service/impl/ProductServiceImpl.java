package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.repository.ProductRepository;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.Local;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product create(Product product) {

        product.setCreatedDate(LocalDateTime.now());

        // transformando em m²
        product.setMeasure(product.getHeight() * product.getWidth() / 10000);

        // setando o custo do produto buscando o valor do m²
        product.setCost(product.getMeasure() * 25);

        return productRepository.save(product);
    }

    @Override
    public Product edit(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

}
