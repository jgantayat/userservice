package com.learnfirst.userservice.service.impl;

import com.learnfirst.userservice.service.DataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Profile("dev")
public class DataServiceImplDEV implements DataService {
    @Override
    public String getData() {
        return "Dev Data Service";
    }
}
