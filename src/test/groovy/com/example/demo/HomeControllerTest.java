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
    public static int port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    void hola(){
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/hola", String.class))
                .isEqualTo("Buenos dias!");
    }
}