package kr.co.apink.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class notice_controller {

	@GetMapping("/notice/list.do")
	public String notice_list() {
		
		return null;
	}
	
}
