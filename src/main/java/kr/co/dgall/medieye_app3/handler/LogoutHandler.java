package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler{


	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		MemberDoctor loginedUser = (MemberDoctor)request.getSession().getAttribute("userInfo");
		log.info("logout에서 꺼낸 User = {}", loginedUser);
		
		// 세션의 userInfo null로 만들기
		request.getSession().setAttribute("userInfo", null);
		request.getSession().invalidate();
		
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
