package com.khaliullov.love_nail;

import com.khaliullov.love_nail.entity.Master;
import com.khaliullov.love_nail.repo.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveNailApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoveNailApplication.class, args);
	}

}
