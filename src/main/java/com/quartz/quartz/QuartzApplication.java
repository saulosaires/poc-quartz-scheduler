package com.quartz.quartz;

import com.quartz.quartz.service.FtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootApplication
public class QuartzApplication implements CommandLineRunner {

	@Autowired
	private FtpService ftpService;

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	public void run(String... args) throws Exception {

		Path path = Paths.get("teste.txt");

		ftpService.alumniUpload(path);

	}

}
