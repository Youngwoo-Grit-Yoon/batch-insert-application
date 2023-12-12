package com.example.demo.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Configuration
public class SingletonBeanConfig {
    @Value("${json-data-file-path}")
    private String jsonDataFilePath;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JsonObject jsonDataFile() throws FileNotFoundException {
        return new Gson().fromJson(new JsonReader(new FileReader(jsonDataFilePath)), JsonObject.class);
    }
}
