package kr.co.apink;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("movie_DTO")
public class movie_DTO {
	//movie_res
	/*
	 midx : auto_increment 
	 moviecode : 영화관 고유코드
	 usercode : 고객의 고유코드
	 mperson : 예매 인원수
	 mname : 고객명 
	 movienm : 영화명
	 mdate : 예매일자
	 mtime : 예매시간
	 mtoday : 예매신청일자
	 */
	int midx, moviecode,usercode,mperson;
	String mname,movienm,mdate,mtime,mtoday;
	
	//movie_seat 
	//seatno : 예매 좌석번호 배정
	int sidx,seatno;
}
