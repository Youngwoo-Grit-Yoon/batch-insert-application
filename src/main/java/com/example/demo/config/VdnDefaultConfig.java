package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vdn")
@Data
public class VdnDefaultConfig {
    private String centerId;
    private String serverId;
    private boolean rollback;
}
