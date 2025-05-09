package kr.co.apink.notice;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class notice_controller {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	PrintWriter pw = null;
	
	@Autowired
	notice_DAO ndao;
	
	//API 통신(POST)
	@PostMapping("/idcheck.do")
	public String idcheck(@RequestParam("mid")String mid,
			ServletResponse res) throws Exception {
		this.pw = res.getWriter();
		
		//select로 DB의 count 정보를 받은 후 조건에 맞는 값을 Front-end에 전달
		Integer result = this.ndao.test_member(mid);
		if(result > 0) {
			this.pw.print("no");
		}else {
			this.pw.print("yes");
		}
		this.pw.close();
		return null;
	}
	
	
	
	//API 통신 (GET)
	@GetMapping("/notice/eacheck.do")
	public String notice_ea(@RequestParam("ea") int ea,
			ServletResponse res) throws Exception {
		this.pw = res.getWriter();
		//DBMS의 부화를 최소화 하기 위해서 잠시 프로세서를 중지 후 가동
		Thread.sleep(3000);
		Integer total = this.ndao.notice_count();
		String msg = "no";
		if(ea < total) {
			msg = "ok";
		}
		this.pw.print(msg);
		return null;
	}
	
	
	@GetMapping("/notice/list.do")
	public String notice_list(Model m) {
		
		List<notice_DTO> all = this.ndao.notice_list();
		m.addAttribute("all",all);
		m.addAttribute("ea",all.size());	//데이터 총 갯수
		
		return "/subpage.html";
	}
	
}
