package com.xyf;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.xyf.dao")
@EnableTransactionManagement
public class XyfApplication /*extends SpringBootServletInitializer*/ {
	private static final Logger log = LoggerFactory.getLogger(XyfApplication.class);

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(XyfApplication.class);
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(XyfApplication.class, args);
		log.info("\n----------------------------------------------------------\n\t" +
				"服务端 '{}' 启动完成! \n\t" +
				"环境(s): \t{}\n----------------------------------------------------------");
	}
}
