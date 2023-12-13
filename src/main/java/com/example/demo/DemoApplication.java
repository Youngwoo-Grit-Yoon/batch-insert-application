package com.example.demo;

import com.example.demo.config.VdnDefaultConfig;
import com.example.demo.data.VdnData;
import com.example.demo.jpa.Vdn;
import com.example.demo.jpa.VdnRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	@Qualifier("jsonDataFile")
	private JsonObject jsonDataFile;
	private final VdnRepository vdnRepository;
	private final VdnDefaultConfig vdnDefaultConfig;
	private List<Vdn> vdnList;

	public DemoApplication(VdnRepository vdnRepository, VdnDefaultConfig vdnDefaultConfig) {
		this.vdnRepository = vdnRepository;
		this.vdnDefaultConfig = vdnDefaultConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			JsonArray vdnJsonArray = this.jsonDataFile.get("vdn_list").getAsJsonArray();
			this.vdnList = new ArrayList<>();
			VdnData vdnData;

			for (JsonElement jsonElement : vdnJsonArray) {
				vdnData = new VdnData(jsonElement.getAsJsonObject());
				this.vdnList.add(new Vdn(
						vdnData.getVdnNo(),
						vdnDefaultConfig.getCenterId(),
						vdnDefaultConfig.getServerId(),
						vdnData.getMonitor(),
						vdnData.getType(),
						vdnData.getSplit(),
						vdnData.getCheckLink(),
						vdnData.getComment(),
						vdnData.getResult()
				));
			}

			if (this.vdnDefaultConfig.isRollback()) {
				saveWithRollback();
			} else {
				saveWithoutRollback();
			}
		};
	}

	void saveWithRollback() {
		try {
			this.vdnRepository.saveAll(this.vdnList);
		} catch (Exception e) {
			System.out.println(MessageFormat.format("""
					VDN 데이터를 배치 삽입하는 도중 에러가 발생하였습니다. 하기 내용을 확인해주세요.
					{0}""", e.getMessage()));
		}
	}

	void saveWithoutRollback() {
		for (Vdn vdn : this.vdnList) {
			try {
				this.vdnRepository.save(vdn);
			} catch (Exception e) {
				System.out.println(MessageFormat.format("""
					VDN 데이터를 개별 삽입하는 도중 에러가 발생하였습니다. 하기 내용을 확인해주세요.
					{0}""", e.getMessage()));
			}
		}
	}
}
