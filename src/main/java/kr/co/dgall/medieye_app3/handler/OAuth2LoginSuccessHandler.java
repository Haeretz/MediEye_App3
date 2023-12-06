package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.mapper.LoginLogMapper;
import kr.co.dgall.medieye_app3.model.LoginLog;
import kr.co.dgall.medieye_app3.model.SnsMember;
import kr.co.dgall.medieye_app3.service.LoginService;
import kr.co.dgall.medieye_app3.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private LoginService loginService;
	
	private final LoginLogMapper loginLogMapper;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

		
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

		System.out.println("isAuthenticated: "+authentication.isAuthenticated());
        System.out.println("getName: "+authentication.getName());
        System.out.println("getID: "+authentication);
        
     // SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        
		
		SnsMember snsMember = new SnsMember();
		snsMember.setSnsId((String)oAuth2User.getAttributes().get("id"));	
		snsMember.setEmail((String)oAuth2User.getAttributes().get("email"));
		snsMember.setSnsType((String)oAuth2User.getAttributes().get("snsType"));
		SnsMember resultMember = loginService.memberCheck(snsMember);
		
		if(resultMember == null || resultMember.getMemberId() == null) {
			log.info("resultMember : {}", resultMember);
			HttpSession session = request.getSession();
			session.setAttribute("SnsMemberId", resultMember.getSnsId());
			response.sendRedirect("/join?email=" + snsMember.getEmail());
		}else {
			
//			 쿠키에 세션id 담기
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", snsMember);
			Cookie cookie = new Cookie("sessionId", session.getId());
			response.addCookie(cookie);
			
			//LoginLog 객체 만들어서 insert
			LoginLog loginLog = new LoginLog();
			loginLog.setEmail((String)oAuth2User.getAttributes().get("email"));
			loginLog.setIp(Utils.getClientIp(request));
			loginLog.setLoginSuccessYn("Y");
			loginLog.setLoginType("normal");
			loginLog.setLogoutYn("N");
			loginLog.setUserAgent(Utils.getClientUserAgent(request));
			loginLogMapper.insertLog(loginLog);
			log.info("입력된 Log: {}", loginLog);
			
			response.sendRedirect("/loginSuccess");	
		}
		
    }
	
}
