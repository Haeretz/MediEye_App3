package kr.co.dgall.medieye_app3.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String joinForm(HttpServletRequest req, HttpServletResponse res, @Param("email1") String email1, @Param("email2") String email2, @Param("snsId") String snsId, @Param("snsType") String snsType, Model model) throws Exception {
		
		log.info("email-id : {}", email1);
		log.info("email-domain : {}", email2);
		log.info("url :{}", req.getRequestURL());
		
		if(email1 != null && !isValidEmail(email1)) {
			model.addAttribute("invalidEmail", true);
		}else {
			model.addAttribute("invalidEmail", false);
		}
		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);
		model.addAttribute("snsId", snsId);
		model.addAttribute("snsType", snsType);
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
		joinService.join(member);
		return "login";
	}
	
	/** 이메일 중복체크   */
	@PostMapping("/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam HashMap<String,String> userInput) {
		String email = userInput.get("userInput");
		log.info(email);
		boolean isDuplicate = joinService.checkId(email);
		return isDuplicate;
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
