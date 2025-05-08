package kr.co.apink.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class notice_controller {

	@Autowired
	notice_DAO ndao;
	
	@GetMapping("/notice/list.do")
	public String notice_list(Model m) {
		
		List<notice_DTO> all = this.ndao.notice_list();
		m.addAttribute("all",all);
		
		return "/subpage.html";
	}
	
}
