package kr.co.apink.notice;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("notice_DTO")
public class notice_DTO {
	int nidx, nview;
	String subject,writer,pw,texts,filenm,nfile,ndate;
}




