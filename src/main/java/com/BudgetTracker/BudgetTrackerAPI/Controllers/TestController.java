package com.BudgetTracker.BudgetTrackerAPI.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/")
    public String hello() {
        int x = 5;
        int y = 2;

        return x + " + " + y + " = " + (x + y);
    }

    @GetMapping(path = "/hello")
    public String oof() {
        String x = "hello";

        return "hello " + x;
    }
}
