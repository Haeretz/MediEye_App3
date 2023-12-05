package kr.co.dgall.medieye_app3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String Login() {
		return "login";
	}
	
	@PostMapping("/userlogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response, MemberDoctor memberDoctor ) {
		loginService.login(memberDoctor.getEmail());		
	}
	
	@GetMapping("/loginSuccess")
	public String LoginSuccess() {
		return "loginSuccess";
	}

	@GetMapping("/myPage")
	public String myPage() {
		return "myPage";
	}
}
