package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class DemoApplication {

	static void main(String[] args) {
		SpringApplication.run(DemoApplication, args)
	}

	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "Vadym Urvachov") String name) {
		return String.format("Proyecto Spring Boot de  %s!", name);
	}

	@GetMapping("/gradle")
	public String gradle(@RequestParam(value = "name", defaultValue = "Gradle:1.0.0") String name) {
		return String.format("Este proyecto es de:  %s", name);
	}

	@GetMapping("/suma")
	public @ResponseBody Float suma(@RequestParam(name="a") Float a, @RequestParam(name="b") Float b){
		return a + b;
	}
}
