package kr.co.dgall.medieye_app3.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.dgall.medieye_app3.handler.OAuth2LoginSuccessHandler;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	
	private final OAuth2LoginSuccessHandler successHandler;
	private final CustomOAuth2UserService oAuth2UserService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors().disable()
			.csrf().disable()		
			.httpBasic().disable()
			.formLogin().disable()
			.authorizeRequests()
				.antMatchers("/", "/userlogin","/join","/login","/joinuser").permitAll()
//				.antMatchers("/loginSuccess").authenticated()
				.anyRequest().authenticated()
				//.and().formLogin().loginPage("/login")
			.and()
				.logout()
					.logoutSuccessUrl("/login")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 post로 인식해서 수행됨
					.addLogoutHandler(new LogoutHandler() {
						
						@Override
						public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
							MemberDoctor loginedUser = (MemberDoctor)request.getSession().getAttribute("userInfo");
							log.info("logout에서 꺼낸 User = {}", loginedUser);
							
							request.getSession().setAttribute("userInfo", null);
							request.getSession().invalidate();
							
							try {
								response.sendRedirect("/login");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					})
					.deleteCookies("JSESSIONID")
					//.invalidateHttpSession(true)
					.clearAuthentication(true)
			.and()
	            .oauth2Login()
		            .loginPage("/login") // 로그인페이지 uri
		            .successHandler(successHandler)
		            .userInfoEndpoint()
		            .userService(oAuth2UserService);
				
		return http.build();
	}
	
}
