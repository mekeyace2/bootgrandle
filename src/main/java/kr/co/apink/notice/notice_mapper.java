package kr.co.apink.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface notice_mapper {
	List<notice_DTO> notice_list();	
	Integer notice_count();
}
