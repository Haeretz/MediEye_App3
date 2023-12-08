package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.mapper.LoginLogMapper;
import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.mapper.SnsMemberMapper;
import kr.co.dgall.medieye_app3.model.LoginLog;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final MemberDoctorMapper memberDoctorMapper;
	private final LoginLogMapper loginLogMapper;
	private final SnsMemberMapper snsMemberMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		MemberDoctor userInfo = memberDoctorMapper.getMemberDoctor(authentication.getName()); 
		log.info("authentication: {}", userInfo);
		
		//LoginLog 객체 만들어서 insert
		LoginLog loginLog = new LoginLog();
		loginLog.setEmail(userInfo.getEmail());
		loginLog.setIp(Utils.getClientIp(request));
		loginLog.setLoginSuccessYn("Y");
		loginLog.setLoginType("normal");
		loginLog.setLogoutYn("N");
		loginLog.setUserAgent(Utils.getClientUserAgent(request));
		loginLogMapper.insertLog(loginLog);
		log.info("입력된 Log: {}", loginLog);
	
		// 로그인 성공일시, 로그인 시도 일시, 로그인 실패 횟수 초기화, 로그인 로그 기록
		memberDoctorMapper.updateLoginSuccessMemberDoctor(userInfo);
//		loginLogMapper.insertLog(log);
		
		HttpSession session = request.getSession();
		// userInfo 보안 어떻게??
		session.setAttribute("userInfo", userInfo);
		Cookie cookie = new Cookie("sessionId", session.getId());
		response.addCookie(cookie); 
		
		response.sendRedirect("/loginSuccess");
	}
}
