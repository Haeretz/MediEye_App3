package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.model.SnsMember;
import kr.co.dgall.medieye_app3.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private LoginService loginService;
	
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
			response.sendRedirect("/join?email=" + snsMember.getEmail());
		}else {
			
			response.sendRedirect("/loginSuccess");	
		}
		
    }
	
}
