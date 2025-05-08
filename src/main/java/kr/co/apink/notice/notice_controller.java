package kr.co.apink.notice;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class notice_controller {

	PrintWriter pw = null;
	
	@Autowired
	notice_DAO ndao;
	
	@GetMapping("/notice/eacheck.do")
	public String notice_ea(@RequestParam("ea") int ea,
			ServletResponse res) throws Exception {
		//GenericServlet res
		//HttpServletResponse res	
		//ServletResponse res
		this.pw = res.getWriter();
		
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
