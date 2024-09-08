package com.infnet.geeklib.service.impl;

import com.infnet.geeklib.model.ProductLog;
import com.infnet.geeklib.repository.ProductLogRepository;
import com.infnet.geeklib.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductLogServiceImpl implements ProductLogService {

    private final ProductLogRepository productLogRepository;

    @Override
    public List<ProductLog> getAllLogs() {
        return productLogRepository.findAll();
    }
}
