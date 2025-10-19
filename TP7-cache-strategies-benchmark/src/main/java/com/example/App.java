package com.example;

import com.example.service.DataInitService;
import com.example.service.PerformanceTestService;

public class App {

    public static void main(String[] args) {
        DataInitService initService = new DataInitService();
        initService.initData();

        PerformanceTestService testService = new PerformanceTestService();
        testService.testCachePerformance();

        initService.close();
        testService.close();
    }
}
