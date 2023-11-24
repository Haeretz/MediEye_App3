package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler{

	private final MemberDoctorMapper memberDoctorMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// 로그인 실패시 해당 아이디에 로그인 실패횟수 증가 및 로그인 시도 일시 업데이트
		memberDoctorMapper.updateLoginFailMemberDoctor(request.getParameter("email"));
		
		String errorMsg = null;
		
		if(exception instanceof BadCredentialsException) {
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = MessageUtils.getMessage("error.BadCredentials");
		} else if(exception instanceof DisabledException) {
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = MessageUtils.getMessage("error.Disaled");
		} else if(exception instanceof InternalAuthenticationServiceException){
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = MessageUtils.getMessage("error.InternalAuthenticationService");
		} else {
//			errorMsg = messageUtils.getMessage("error.BadCredentials");
			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
		}
		
//		errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
//		response.sendRedirect("/login");
		request.setAttribute("errorMsg", errorMsg);
		log.info("errorMsg :{}", errorMsg);
		request.getRequestDispatcher("/login").forward(request, response);
		
	}

}
