package com.example.demo;

import com.example.demo.config.VdnDefaultConfig;
import com.example.demo.data.JsonDataFile;
import com.example.demo.data.VdnData;
import com.example.demo.jpa.Vdn;
import com.example.demo.jpa.VdnRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public CommandLineRunner run(VdnRepository vdnRepository, VdnDefaultConfig vdnDefaultConfig) {
		return (args) -> {
			JsonArray vdnJsonArray = this.jsonDataFile.getJsonObject().get("vdn_list").getAsJsonArray();
			int count = 0;
			for (JsonElement jsonElement : vdnJsonArray) {
				// 데이터 파싱
				count++;
				VdnData vdnData = new VdnData(jsonElement.getAsJsonObject());
				System.out.println(MessageFormat.format("""
						> {0}번째 데이터 삽입
						{1}""", count, vdnData.toString()));

				// 데이터 삽입
				try {
					vdnRepository.save(new Vdn(
							vdnData.getVdnNo(),
							vdnDefaultConfig.getCenterId(),
							vdnDefaultConfig.getServerId(),
							vdnData.getMonitor(),
							vdnData.getType(),
							vdnData.getSplit(),
							vdnData.getCheckLink(),
							vdnData.getComment(),
							vdnData.getResult()));
				} catch (Exception e) {
					System.out.println(MessageFormat.format("""
							{0}번째 데이터 삽입 도중 에러가 발생하였습니다. 자세한 내용은 아래 내용을 확인하세요.
							{1}""", count, e.getMessage()));
				}
			}
		};
	}
}
