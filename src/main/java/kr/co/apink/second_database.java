package kr.co.apink;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;

@Configuration
//Cloud Mysql
public class second_database {
	Properties pp = new Properties();
	String pname = "database.properties";	//properties의 파일명
	
	public second_database() throws Exception {
		this.pp.load(this.getClass().getClassLoader().getResourceAsStream(pname));
		this.pp.setProperty("spring.datasource.driver-class-name", this.pp.getProperty("spring.second.datasource.driver-class-name"));
		this.pp.setProperty("spring.datasource.url", this.pp.getProperty("spring.second.datasource.url"));
		this.pp.setProperty("spring.datasource.username", this.pp.getProperty("spring.second.datasource.username"));
		this.pp.setProperty("spring.datasource.password", this.pp.getProperty("spring.second.datasource.password"));
	}
	
	
	
	
	
}
