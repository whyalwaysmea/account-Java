package com.whyalwaysmea.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: Long
 * @Date: Create in 2018/3/22 14:10
 * @Description:
 * 使用了@MapperScan 就可以不用在每个Mapper上用@Mapper
 */
@SpringBootApplication
@MapperScan(basePackages = "com.whyalwaysmea.account.mapper")
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("start success");
	}
}
