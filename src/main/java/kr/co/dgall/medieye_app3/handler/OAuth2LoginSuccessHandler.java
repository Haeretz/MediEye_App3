package kr.co.dgall.medieye_app3.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.dgall.medieye_app3.mapper.LoginLogMapper;
import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.model.LoginLog;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	
	private final LoginLogMapper loginLogMapper;
	private final MemberDoctorMapper memberDoctorMapper;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

		
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

		System.out.println("isAuthenticated: "+authentication.isAuthenticated());
        System.out.println("getName: "+authentication.getName());
        System.out.println("getID: "+authentication);
        
     // SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        
        // MemberDoctor 조회
        MemberDoctor checkMember = memberDoctorMapper.getMemberDoctor(oAuth2User.getName());
        
        // 이메일로 Member null 체크
        if(checkMember != null) {
	        // SNS 회원가입한 기록 유무 확인
	        if(checkMember.getSnsId() != null) {
	        	
	        	// LoginLog insert
				LoginLog loginLog = new LoginLog();
				loginLog.setEmail((String)oAuth2User.getAttributes().get("email"));
				loginLog.setIp(Utils.getClientIp(request));
				loginLog.setLoginSuccessYn("Y");
				loginLog.setLoginType("normal");
				loginLog.setLogoutYn("N");
				loginLog.setUserAgent(Utils.getClientUserAgent(request));
				loginLogMapper.insertLog(loginLog);
				log.info("입력된 Log: {}", loginLog);
				
				// 세션에 email 담기
				HttpSession session = request.getSession(false);
				String sessionId = session.getId();
				log.info("JSESSIONID : {}", sessionId);
				session.setAttribute("userEmail", checkMember.getEmail());
				
				response.sendRedirect("/loginSuccess");
	        }else { 
	        	// checkMember.getSnsId() == null 
	        	response.sendRedirect("/logout?errormsg=" + "해당 이메일은 이미 가입되어 있습니다.");
	        }
	    }else { 
	    	// MemberDoctor의 해당 email이 없는 경우 회원가입 진행
	    	String[] emailParts = oAuth2User.getName().split("@");
	    	String param1 = emailParts[0];
	    	String param2 = emailParts[1];
    		String param3 = oAuth2User.getAttribute("id");
    		String param4 = oAuth2User.getAttribute("snsType");
			response.sendRedirect("/join?email1=" + param1 + "&email2=" + param2 + "&snsId=" + param3 + "&snsType=" + param4); 
	    }
	}
}


