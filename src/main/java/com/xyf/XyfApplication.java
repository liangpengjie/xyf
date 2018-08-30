package com.xyf;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xyf.dao")
public class XyfApplication {
	private static final Logger log = LoggerFactory.getLogger(XyfApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(XyfApplication.class, args);
		log.info("\n----------------------------------------------------------\n\t" +
				"服务端 '{}' 启动完成! \n\t" +
				"环境(s): \t{}\n----------------------------------------------------------");
	}
}
