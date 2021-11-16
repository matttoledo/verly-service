package com.verly.verlyservice.application.service;

import com.verly.verlyservice.application.model.CashFlow;
import com.verly.verlyservice.application.model.Product;

import java.util.List;

public interface CashFlowService {
    List<CashFlow> findAll();

    CashFlow save(CashFlow cashFlow);

    void delete(CashFlow cashFlow);
}
