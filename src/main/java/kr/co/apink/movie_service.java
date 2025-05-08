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
	
	
	public List<store_DTO> store_all(){
		//List<store_DTO> all = this.mapper.store_all();
		return null;
	}
	public List<movie_DTO> movie_all(){
		//List<movie_DTO> all = this.mapper.movie_all();
		List<movie_DTO> all = this.sql1.selectList("movie_all"); 
		return all;
	}
	
}
