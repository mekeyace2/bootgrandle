package kr.co.apink;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
	
	@Bean(name="mysql2")	//Mybatis가 구동시 각 class별로 @Bean 다 작동시킴
	public DataSource datasource() {
		//DataSource를 변경시 Mybatis에서 Database 서버 정보를 변경함
		String classnm = this.pp.getProperty("spring.datasource.driver-class-name");
		String url = this.pp.getProperty("spring.datasource.url");
		String user = this.pp.getProperty("spring.datasource.username");
		String pass = this.pp.getProperty("spring.datasource.password");
				
		//DataSource 정보를 저장하는 배열값
		DataSourceBuilder<?> dsb = DataSourceBuilder.create();
		dsb.driverClassName(classnm);
		dsb.url(url);
		dsb.username(user);
		dsb.password(pass);
		return dsb.build();
	}
	
	@Bean(name = "sqlfactory2")
	public SqlSessionFactory sqlfactory(@Qualifier("mysql2")DataSource ds,
			ApplicationContext ac) throws Exception{
		SqlSessionFactoryBean sqlf = new SqlSessionFactoryBean();
		sqlf.setDataSource(ds);		
		sqlf.setMapperLocations(ac.getResources(this.pp.getProperty("mybatis.mapper-locations")));
		return sqlf.getObject();
	}
	
	@Bean(name = "sqltemplate2")
	public SqlSessionTemplate sqltemplate(@Qualifier("sqlfactory2")SqlSessionFactory sf) throws Exception{
		SqlSessionTemplate stp = new SqlSessionTemplate(sf);
		return stp;
	}
		
	
}
