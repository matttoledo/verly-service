package com.verly.verlyservice.application.repository;

import com.verly.verlyservice.application.model.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

}
