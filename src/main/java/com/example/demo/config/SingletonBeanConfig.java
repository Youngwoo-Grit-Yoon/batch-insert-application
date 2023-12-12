package com.example.demo.config;

import com.example.demo.data.JsonDataFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileNotFoundException;

@Configuration
public class SingletonBeanConfig {
    @Value("${json-data-file-path}")
    private String jsonDataFilePath;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JsonDataFile jsonDataFile() throws FileNotFoundException {
        return new JsonDataFile(this.jsonDataFilePath);
    }
}
