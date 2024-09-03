package com.application.utility.practice1.chapter06_swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/swagger")
public class SwaggerController {

	
	@GetMapping("/basic")
	public String basic(@RequestParam("basicInput") String basicInput) {
		System.out.println("-- basic --");
		System.out.println("basicInput : " + basicInput);
		return "응애";
	}
	
	@Operation(summary = "swagger 실습" , description = "swagger 실습 튜토리얼 입니다.")
	@GetMapping("/operation")
	public String operation() {
		
		return "Operation Output Data";
	}
}
