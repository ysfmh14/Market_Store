package com.example.market_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarketStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketStoreApplication.class, args);
    }

}
