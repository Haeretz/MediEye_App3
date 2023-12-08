package kr.co.dgall.medieye_app3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String Login(@RequestParam(name="error", required= false) String errorMsg, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
//		if (auth != null && auth.getDetails() instanceof WebAuthenticationDetails) {
//		    WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
//		    String sessionId = details.getSessionId();
//		    log.info("현재 sessionId : {}", sessionId);
//		    
//		    if( sessionId != null) {
//		    	return "loginSuccess";
//		    }
//		}
		model.addAttribute("errorMsg", errorMsg);
		log.info("errorMsg:{}", errorMsg);
		return "login";
	}
	
	@PostMapping("/userlogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response, MemberDoctor memberDoctor ) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies
		
		loginService.login(memberDoctor.getEmail());		
	}
	
	@GetMapping("/loginSuccess")
	public String LoginSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession(false);
		MemberDoctor userInfo = (MemberDoctor) session.getAttribute("userInfo");
		log.info("userinfo : {}", userInfo);
		String username = userInfo.getUsername();
		model.addAttribute("username", username);
		return "loginSuccess";
	}

	@GetMapping("/myPage")
	public String myPage() {
		return "myPage";
	}
}
