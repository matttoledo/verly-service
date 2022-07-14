package com.verly.verlyservice.application.repository;

import com.verly.verlyservice.application.model.TemperedGlassCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCostRepository extends JpaRepository<TemperedGlassCost, Long> {


}
