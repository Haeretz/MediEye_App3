package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private MemberDoctor memberDoctor;
	
	@Autowired
	private LoginService loginServicel;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		memberDoctor = (MemberDoctor)request.getUserPrincipal();
		loginServicel.login(memberDoctor);
		log.info("login Success!!");
		response.sendRedirect("/loginSuccess");
			
	}
	
	

}
