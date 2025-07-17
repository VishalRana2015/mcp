package com.example.demo;

import com.example.demo.service.CalculatorService;
import com.example.demo.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider registerTools(WeatherService weatherService, CalculatorService calculatorService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService, calculatorService).build();
	}

}
