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

@Configuration
public class third_database {
	Properties pp = new Properties();
	String pname = "database.properties";
	
	public third_database() throws Exception{
		this.pp.load(this.getClass().getClassLoader().getResourceAsStream(pname));
		this.pp.setProperty("spring.datasource.driver-class-name", this.pp.getProperty("spring.third.datasource.driver-class-name"));
		this.pp.setProperty("spring.datasource.url", this.pp.getProperty("spring.third.datasource.url"));
		this.pp.setProperty("spring.datasource.username", this.pp.getProperty("spring.third.datasource.username"));
		this.pp.setProperty("spring.datasource.password", this.pp.getProperty("spring.third.datasource.password"));
	}
	
	@Bean(name="oracle")
	public DataSource datasource() {
		String classnm = this.pp.getProperty("spring.datasource.driver-class-name");
		String url = this.pp.getProperty("spring.datasource.url");
		String user = this.pp.getProperty("spring.datasource.username");
		String pass = this.pp.getProperty("spring.datasource.password");
		DataSourceBuilder<?> dsb = DataSourceBuilder.create();
		dsb.driverClassName(classnm);
		dsb.url(url);
		dsb.username(user);
		dsb.password(pass);
		return dsb.build();
	}
	
	@Bean(name = "sqlfactory3")
	public SqlSessionFactory sqlfactory(@Qualifier("oracle")DataSource ds,
			ApplicationContext ac) throws Exception{
		SqlSessionFactoryBean sqlf = new SqlSessionFactoryBean();
		sqlf.setDataSource(ds);		
		sqlf.setMapperLocations(ac.getResources(this.pp.getProperty("mybatis.mapper-locations")));
		return sqlf.getObject();
	}
	
	@Bean(name = "sqltemplate3")
	public SqlSessionTemplate sqltemplate(@Qualifier("sqlfactory3")SqlSessionFactory sf) throws Exception{
		SqlSessionTemplate stp = new SqlSessionTemplate(sf);
		return stp;
	}
	
	
}
