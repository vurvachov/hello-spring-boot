package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplication2 {

    @RequestMapping("/")
    public String home() {
        return "Spring Boot con Docker en el proyecto de Vadym Urvachov";
    }

}