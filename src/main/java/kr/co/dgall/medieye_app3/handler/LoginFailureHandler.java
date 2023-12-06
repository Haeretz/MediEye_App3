package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.mapper.LoginLogMapper;
import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.model.LoginLog;
import kr.co.dgall.medieye_app3.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler{

	private final MemberDoctorMapper memberDoctorMapper;
	private final LoginLogMapper loginLogMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// 로그인 실패시 해당 아이디에 로그인 실패횟수 증가 및 로그인 시도 일시 업데이트
		memberDoctorMapper.updateLoginFailMemberDoctor(request.getParameter("email"));
		
		// 로그인 실패시 LoginLog 객체 만들어서 insert
		LoginLog loginLog = new LoginLog();
		loginLog.setEmail(request.getParameter("email"));
		loginLog.setIp(Utils.getClientIp(request));
		loginLog.setLoginSuccessYn("N");
		loginLog.setLoginType("normal");
		loginLog.setLogoutYn(null);
		loginLog.setUserAgent(Utils.getClientUserAgent(request));
		loginLogMapper.insertLog(loginLog);
		log.info("실패시 입력된 Log: {}", loginLog);
		
		String errorMsg = null;
		
		if(exception instanceof BadCredentialsException) {
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = Utils.getMessage("error.BadCredentials");
		} else if(exception instanceof DisabledException) {
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = Utils.getMessage("error.Disaled");
		} else if(exception instanceof InternalAuthenticationServiceException){
//			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
			errorMsg = Utils.getMessage("error.InternalAuthenticationService");
		} else {
//			errorMsg = messageUtils.getMessage("error.BadCredentials");
			errorMsg = "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.";
		}
		
		errorMsg = URLEncoder.encode(errorMsg, StandardCharsets.UTF_8);
//		response.sendRedirect("/login");
		request.setAttribute("errorMsg", errorMsg);
		log.info("errorMsg :{}", errorMsg);
		
		response.sendRedirect("/login?error=" + errorMsg);
	}

}
