package com.verly.verlyservice.application.controller;

import com.verly.verlyservice.application.model.product.Product;
import com.verly.verlyservice.application.model.product.ProductDTO;
import com.verly.verlyservice.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("product")

public class ProductController {
    private final ProductService productService;

    @GetMapping
    private ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    private ResponseEntity<Product> create(@RequestBody ProductDTO product) {
        if (Objects.isNull(product))
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productService.create(product));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Product> edit(@RequestBody ProductDTO product, @PathVariable("id") Long id){
        return ResponseEntity.ok(productService.edit(product));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ProductDTO> delete(@RequestBody ProductDTO product, @PathVariable("id") Long id){
        return ResponseEntity.ok(product);
    }

}
