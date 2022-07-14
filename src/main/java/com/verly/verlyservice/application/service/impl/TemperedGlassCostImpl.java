package com.verly.verlyservice.application.service.impl;

import com.verly.verlyservice.application.model.TemperedGlassCost;
import com.verly.verlyservice.application.repository.ProductCostRepository;
import com.verly.verlyservice.application.service.TemperedGlassCostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class TemperedGlassCostImpl implements TemperedGlassCostService {

    private final ProductCostRepository productCostRepository;

    @Override
    public TemperedGlassCost recover() {
        return productCostRepository.findAll().get(0);
    }
}
