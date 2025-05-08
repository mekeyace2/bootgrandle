package kr.co.apink;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface mapper {
	public List<movie_DTO> movie_all();
	public List<store_DTO> store_all();	
}
