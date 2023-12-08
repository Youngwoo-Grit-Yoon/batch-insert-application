package com.example.demo.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonDataFile {
    private final JsonObject jsonObject;

    public JsonDataFile(String jsonDataFilePath) throws FileNotFoundException {
        this.jsonObject = new Gson().fromJson(new JsonReader(new FileReader(jsonDataFilePath)), JsonObject.class);
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}