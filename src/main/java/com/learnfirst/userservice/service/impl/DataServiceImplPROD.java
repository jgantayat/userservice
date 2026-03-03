package com.learnfirst.userservice.service.impl;

import com.learnfirst.userservice.service.DataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class DataServiceImplPROD implements DataService {
    @Override
    public String getData() {
        return "Prod Data Service";
    }
}
