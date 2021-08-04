package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort
    private static int port;

    @Autowired
    private static TestRestTemplate restTemplate;

    @Test
    void hola(){
        assertThat(restTemplate.getForObject("http://localhost:"+port+"/hola", String.class))
                .isEqualTo("Buenos dias!");
    }
}