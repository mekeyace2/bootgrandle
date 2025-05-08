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

//실서버 MariaDB
@Configuration
//aop, proxy class, Databaseconfig 를 사용할 때 적용하는 어노테이션
//@EnableTransactionManagement	
public class first_database {
	//Properties 파일을 로드할 수 있는 라이브러리
	Properties pp = new Properties();
	String pname = "database.properties";	//properties의 파일명
	
	//MariaDB 정보 셋팅 (즉시실행 메소드)
	public first_database() throws Exception {
		this.pp.load(this.getClass().getClassLoader().getResourceAsStream(pname));
		this.pp.setProperty("spring.datasource.driver-class-name", this.pp.getProperty("spring.first.datasource.driver-class-name"));
		this.pp.setProperty("spring.datasource.url", this.pp.getProperty("spring.first.datasource.url"));
		this.pp.setProperty("spring.datasource.username", this.pp.getProperty("spring.first.datasource.username"));
		this.pp.setProperty("spring.datasource.password", this.pp.getProperty("spring.first.datasource.password"));
	}
	//Controller보다 먼저 인식됨
	@Bean(name="mysql1")	//Mybatis가 구동시 각 class별로 @Bean 다 작동시킴
	@Primary		//@Bean에서만 사용하는 어노테이션 우선순위를 정하는 어노테이션
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
	//@Qualifier : @Bean name값을 가져와서 객체에 주입하는 방식을 사용할 수 있는 어노테이션
	@Bean(name = "sqlfactory")
	@Primary
	public SqlSessionFactory sqlfactory(@Qualifier("mysql1")DataSource ds,
			ApplicationContext ac) throws Exception{
		SqlSessionFactoryBean sqlf = new SqlSessionFactoryBean();
		sqlf.setDataSource(ds);		
		sqlf.setMapperLocations(ac.getResources(this.pp.getProperty("mybatis.mapper-locations")));
		return sqlf.getObject();
	}
	
	@Bean(name = "sqltemplate")
	@Primary
	public SqlSessionTemplate sqltemplate(@Qualifier("sqlfactory")SqlSessionFactory sf) throws Exception{
		SqlSessionTemplate stp = new SqlSessionTemplate(sf);
		return stp;
	}
	
	
	
	
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
