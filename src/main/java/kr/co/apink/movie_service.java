package kr.co.apink;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class movie_service {
	/*  //1개의 Database 연결시 사용
	@Autowired
	private mapper mapper;
	*/
	
	@Autowired
	@Qualifier(value = "sqltemplate")
	private SqlSession sql1;
	
	@Autowired
	@Qualifier(value = "sqltemplate2")
	private SqlSession sql2;
	
	@Autowired	//Oracle
	@Qualifier(value = "sqltemplate3")
	private SqlSession sql3;
	
	public List<member_DTO> member_all(){
		List<member_DTO> all = this.sql3.selectList("member_all");
		return all;
	}
		
	public List<store_DTO> store_all(){
		//List<store_DTO> all = this.mapper.store_all();
		List<store_DTO> all = this.sql2.selectList("store_all");
		return all;
	}
	public List<movie_DTO> movie_all(){
		//List<movie_DTO> all = this.mapper.movie_all();
		List<movie_DTO> all = this.sql1.selectList("movie_all"); 
		return all;
	}
	
}
