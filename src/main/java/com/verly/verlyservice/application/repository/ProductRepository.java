package com.verly.verlyservice.application.repository;

import com.verly.verlyservice.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
