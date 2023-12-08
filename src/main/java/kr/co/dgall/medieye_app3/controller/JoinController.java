package kr.co.dgall.medieye_app3.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.JoinService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@ControllerAdvice
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	/** 회원가입 폼 */
	@RequestMapping("/join")
	public String joinForm(HttpServletRequest req, HttpServletResponse res, @Param("email") String email, Model model) throws Exception {
		
		log.info("email : {}", email);
		log.info("url :{}", req.getRequestURL());
		
		if(email != null && !isValidEmail(email)) {
			model.addAttribute("invalidEmail", true);
		}else {
			model.addAttribute("invalidEmail", false);
		}
		model.addAttribute("email", email);
		return "join";
	}
	
	/** 이메일 유효성 검사 */
	private boolean isValidEmail(String email) {
		return email.contains("@");
	}
	
	/** 회원가입 process  */
	@PostMapping("/joinuser")
//	public String join(@Valid MemberDoctor member,HttpServletRequest request, Errors errors, Model model) throws ServletException, IOException {
	public String join(@Valid MemberDoctor member, BindingResult bind, HttpServletRequest request, Model model) {
		
		// @valid 오류가 잡히지 않고 실행되는 상황
		if(bind.hasErrors()) {
			// 회원가입 에러시 입력한 값 저장
			 model.addAttribute("member", member);
			
			// 유효성 통과 못한 에러
			Map<String, String> valid = joinService.validateHandler(bind);
			for (String key : valid.keySet()) {
				model.addAttribute(key, valid.get(key));
			}
			return "join";
		}
//		 request 받은거 확인해서 값 join메서드에 파라미터로 넘겨주기
		HttpSession session = request.getSession();
		String snsId = (String)session.getAttribute("SnsMemberId");
		joinService.join(member,snsId);
		return "redirect:login";
	}
	
		// 회원가입 에러처리 진행중 
	    @ExceptionHandler(PSQLException.class)
	    public String handlePSQLException(PSQLException e, RedirectAttributes redirectAttributes) {
	    	String errorMsg=null;
	    	if(e.getMessage().contains("duplicate key value violates unique constraint")) {
	    	errorMsg = "이미 사용중인 이메일 주소입니다. 다른 이메일 주소를 입력해주세요.";
	    	redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
	    	return "redirect:join";
	    	}
	    	errorMsg="데이터베이스 오류가 발생했습니다.";
	    	return "redirect:login";
	    }
	
}
