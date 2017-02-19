package com.ErasmusProject.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FileStoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStoreServiceApplication.class, args);
    }
}
