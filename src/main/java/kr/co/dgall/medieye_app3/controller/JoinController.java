package kr.co.dgall.medieye_app3.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.JoinService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	/** 회원가입 폼 */
	@RequestMapping("/join")
	public String joinForm(HttpServletRequest req, HttpServletResponse res, @Param("email") String email, Model model) {
		log.info("email : {}", email);
		if(email != null && !isValidEmail(email)) {
			model.addAttribute("invalidEmail", true);
		}else {
			model.addAttribute("invalidEmail", false);
		}
		model.addAttribute("email", email);
		return "/join";
	}
	
	/** 이메일 유효성 검사 */
	private boolean isValidEmail(String email) {
		return email.contains("@");
	}
	
	/** 회원가입 process */
	@PostMapping("/joinuser")
	public String join(@Valid MemberDoctor member, Errors errors, Model model) {
		if(errors.hasErrors()) {
			// 회원가입 에러시 입력한 값 저장
			 model.addAttribute("member", member);
			
			// 유효성 통과 못한 에러
			Map<String, String> valid = joinService.validateHandler(errors);
			for (String key : valid.keySet()) {
				model.addAttribute(key, valid.get(key));
			}
			return "join";
		}
		joinService.join(member);
		return "redirect:/login";
	}
}
