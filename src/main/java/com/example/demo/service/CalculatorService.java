package com.example.demo.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Tool(name = "Calculator", description = "Add given two numbers")
    public String add(@ToolParam(required = true) double a, @ToolParam(required = true) double b){
        return "Result : " + ( a + b);
    }

}
