package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.CashFlow;
import com.verly.verlyservice.application.repository.CashFlowRepository;
import com.verly.verlyservice.application.service.CashFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Service
public class CashFlowServiceImpl implements CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    public List<CashFlow> findAll(){
        return cashFlowRepository.findAll();
    }

    public CashFlow save(CashFlow cash){
        return cashFlowRepository.save(cash);
    }


    public void delete(CashFlow cash){
        cashFlowRepository.delete(cash);
    }


}
