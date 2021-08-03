package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import static org.assertj.core.api.Assertions.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canAdd() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/suma?a=10&b=1", String.class))
        .isEqualTo("11.0")
    }

    @Test
    public void canViewInfo() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/gradle?name=Gradle:1.0.0", String.class))
        .isNotBlank()
    }

    @Test
    public void canVieWInfo() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/gradle?name=Gradle:1.0.0", String.class))
                .isEqualTo("Este proyecto es de:  Gradle:1.0.0")
    }
}
