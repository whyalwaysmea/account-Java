package com.whyalwaysmea.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 使用了@MapperScan 就可以不用在每个Mapper上用@Mapper
@MapperScan(basePackages = "com.whyalwaysmea.account.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
