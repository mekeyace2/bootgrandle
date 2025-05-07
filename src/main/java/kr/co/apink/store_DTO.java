package kr.co.apink;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("store_DTO")
public class store_DTO {
	int sidx;
	String sname,scode;
}
