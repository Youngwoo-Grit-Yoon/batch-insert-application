package com.example.demo.config;

import com.example.demo.data.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SingletonBeanConfig {
    @Value("${json-data-file-path}")
    private String jsonDataFilePath;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JsonData getJsonData() {
        return new JsonData(this.jsonDataFilePath);
    }
}