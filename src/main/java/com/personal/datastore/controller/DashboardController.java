package com.personal.datastore.controller;

import com.personal.datastore.dto.DashboardDTO;
import com.personal.datastore.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public DashboardDTO getSummary() {
        return service.getSummary();
    }
}

