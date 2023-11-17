package kr.co.dgall.medieye_app3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String Login() {
		return "login";
	}
	
	@PostMapping("/userlogin")
	public String userLogin(HttpServletRequest request, MemberDoctor memberDoctor ) {
		System.out.println("memberDoctor email :" + memberDoctor.getEmail());
		System.out.println("memberDoctor password :" + memberDoctor.getPassword());
		MemberDoctor result = loginService.login(memberDoctor);
		
		if (result != null) {
			// 세션 작업한다
			return "loginSuccess";	
		} else {
			// 로그인실패
			// 로그인 화면으로 다시 돌아가되 로그인 실패 했다는 얼럿을 띄어 줘야 한다
			return "login";
		}
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
