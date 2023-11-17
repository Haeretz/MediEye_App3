package kr.co.dgall.medieye_app3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.JoinService;

@Controller
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	/** 회원가입 폼 */
	@RequestMapping("/join")
	public String joinForm(HttpServletRequest req, HttpServletResponse res, @Param("email") String email, Model model) {
		System.out.println("email : " + email);
		model.addAttribute("email", email);
//		req.setAttribute("eamil", email);
		return "/join";
	}
	
	@PostMapping("/joinuser")
	public String join(MemberDoctor member) {
		joinService.join(member);
		return "redirect:/login";
	}
}
