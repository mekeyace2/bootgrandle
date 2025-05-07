package kr.co.apink;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//실서버 MariaDB
@Configuration
//aop, proxy class, Databaseconfig 를 사용할 때 적용하는 어노테이션
@EnableTransactionManagement	
public class first_database {
	//Properties 파일을 로드할 수 있는 라이브러리
	Properties pp = new Properties();
	String pname = "database.properties";	//properties의 파일명
	
	//Cloud Mysql 정보 셋팅 (즉시실행 메소드)
	public first_database() throws Exception {
		this.pp.load(this.getClass().getClassLoader().getResourceAsStream(pname));
		this.pp.setProperty("spring.datasource.url", "jdbc:mysql://172.30.1.22:13306/webapi");
		this.pp.setProperty("spring.datasource.username", "webapi");
		this.pp.setProperty("spring.datasource.password", "b402402");
	}
	//Controller보다 먼저 인식됨
	@Bean	//Mybatis가 구동시 각 class별로 @Bean 다 작동시킴
	public DataSource datasource() {
		//DataSource를 변경시 Mybatis에서 Database 서버 정보를 변경함
		String url = this.pp.getProperty("spring.datasource.url");
		String user = this.pp.getProperty("spring.datasource.username");
		String pass = this.pp.getProperty("spring.datasource.password");
		return new DriverManagerDataSource(url,user,pass);
	}
	
	
	/*
	@Bean
	public SqlSessionFactory sqlsession(DataSource dsb) throws Exception{
		SqlSessionFactoryBean sf = new SqlSessionFactoryBean();
		sf.setDataSource(dsb);
		sf.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(this.pp.getProperty("mybatis.mapper-locations")));
		return sf.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqltemplate(SqlSessionFactory sqlsession) throws Exception{
		return new SqlSessionTemplate(sqlsession);
	}
	*/
	
	
	
	
	/*	//Database 정보를 properties의 값을 가져오는 방법 및 변경 방법
	public void test() throws Exception {
		//해당 properties의 파일을 로드 하는 메소드임 
		this.pp.load(this.getClass().getClassLoader().getResourceAsStream(pname));
		//getProperty 해당 파일의 클래스 및 메소드의 값을 가져오는 역활
		String user = this.pp.getProperty("spring.datasource.driver-class-name");
		System.out.println(user);
		
		this.pp.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
		user = this.pp.getProperty("spring.datasource.driver-class-name");
		System.out.println(user);
	}
	*/
	
}
