package com.midproject.pikJ;

import com.midproject.pikJ.entity.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PikJApplication {

	public static void main(String[] args) {
		SpringApplication.run(PikJApplication.class, args);
		System.out.println("-- 시작 --");
	}

}
