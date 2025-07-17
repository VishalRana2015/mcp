package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
@Configuration
public class ResoucesService {

    @Bean
    public List<McpServerFeatures.SyncResourceSpecification> systemResources() {
        var systemInfoResource = new McpSchema.Resource(
                "system://info",
                "System Information",
                "Current system information",
                "application/json",
                null
        );

        var resourceSpec = new McpServerFeatures.SyncResourceSpecification(
                systemInfoResource,
                (exchange, request) -> {
                    var systemInfo = Map.of(
                            "timestamp", System.currentTimeMillis(),
                            "memory", Runtime.getRuntime().totalMemory()
                    );

                    String jsonContent = null;
                    try {
                        jsonContent = new ObjectMapper().writeValueAsString(systemInfo);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return new McpSchema.ReadResourceResult(
                            List.of(new McpSchema.TextResourceContents(
                                    request.uri(),
                                    "application/json",
                                    jsonContent
                            ))
                    );
                }
        );

        return List.of(resourceSpec);
    }

}
