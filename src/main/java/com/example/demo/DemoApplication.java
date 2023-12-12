package com.example.demo;

import com.example.demo.config.VdnConfiguration;
import com.example.demo.data.JsonDataFile;
import com.example.demo.jpa.Vdn;
import com.example.demo.jpa.VdnRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.MessageFormat;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	@Qualifier("jsonDataFile")
	private JsonDataFile jsonDataFile;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(VdnRepository vdnRepository, VdnConfiguration vdnConfiguration) {
		return (args) -> {
			JsonArray vdnJsonArray = this.jsonDataFile.getJsonObject().get("vdn_list").getAsJsonArray();
			int count = 0;
			for (JsonElement jsonElement : vdnJsonArray) {
				count++;
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				String vdnNo = jsonObject.get("vdn_no").getAsString();
				String monitor = jsonObject.get("monitor").getAsString();
				String type = jsonObject.get("type").getAsString();
				String split = jsonObject.get("split").getAsString();
				String checkLink = jsonObject.get("check_link").getAsString();
				String comment = jsonObject.get("comment").getAsString();
				String result = jsonObject.get("result").getAsString();
				System.out.println(MessageFormat.format("""
						> {0}번째 데이터 삽입
						vdn_no     : {1}
						monitor    : {2}
						type       : {3}
						split      : {4}
						check_link : {5}
						comment    : {6}
						result     : {7}""", count, vdnNo, monitor, type, split, checkLink, comment, result));
			}
		};
	}
}
