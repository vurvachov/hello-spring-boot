package com.example.demo

import static org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SmokeTest {

    @Autowired
    private HomeController controller;
    private DemoApplication pruebaSuma;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull()

        assertThat(pruebaSuma.suma(a,b)).isEqualTo(10)
    }


}
