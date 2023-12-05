package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler{


	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		// TODO 현재 세션의 정보를 갖고 로그인로그의 logout여부 컬럼에 업데이트
		log.info("request에서 가져올 수 있는 정보들 : {}", request);
		// 일반 로그아웃 email을 가지고  
		
		// SNS 로그아웃 
		
		
		// 로그아웃 시 세션이 가진 값 null 만드는 작업
//		MemberDoctor loginedUser = (MemberDoctor)request.getSession().getAttribute("userInfo");
//		log.info("logout에서 꺼낸 User = {}", loginedUser);
		// 세션의 userInfo null로 만들기
//		request.getSession().setAttribute("userInfo", null);
//		request.getSession().invalidate();
		
		
		log.info("logout에서 꺼낸 User null 확인 = {}", request.getAttribute("userInfo"));
		
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
