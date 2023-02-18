package com.rish889.rishShop.controller;

import com.rish889.rishShop.dto.GetProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllEmployees() {
        Assertions.assertEquals("iPhone 10",
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/products/1", GetProductDto.class)
                        .getProductName());
    }
}