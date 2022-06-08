package com.verly.verlyservice.application.repository;

import com.verly.verlyservice.application.model.ProductCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCostRepository extends JpaRepository<ProductCost, Long> {
}
