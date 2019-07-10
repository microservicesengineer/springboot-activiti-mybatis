package com.ibm.vms;

import javax.annotation.Resource;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "com.ibm.vms.dao.mapper")
public class VmsApplication {
	
    @Resource
    private SpringProcessEngineConfiguration springProcessEngineConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}
	
}
