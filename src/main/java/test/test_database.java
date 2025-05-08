package test;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //환경설정 어노테이션
public class test_database {

	@Bean
	@ConfigurationProperties(prefix = "spring.first.datasource")
	public DataSource first() {
		return DataSourceBuilder.create().type(DataSource.class).build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.second.datasource")
	public DataSource second() {
		return DataSourceBuilder.create().type(DataSource.class).build();
	}

}
