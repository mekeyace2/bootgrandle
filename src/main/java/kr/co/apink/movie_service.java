package kr.co.apink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class movie_service {

	@Autowired
	private mapper mapper;
	
	public List<store_DTO> store_all(){
		List<store_DTO> all = this.mapper.store_all();
		return all;
	}
	
	
	public List<movie_DTO> movie_all(){
		List<movie_DTO> all = this.mapper.movie_all();
		return all;
	}
	
}
