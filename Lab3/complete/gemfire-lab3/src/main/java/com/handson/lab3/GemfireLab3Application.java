package com.handson.lab3;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GemfireLab3Application {

    public static void main(String[] args) {
        SpringApplication.run(GemfireLab3Application.class, args);
        
        Account a1 = new Account("id1", "John Doe", 100.0);
        Account a2 = new Account("id2", "Jane Doe", 50.0);
        
        
    }
}
