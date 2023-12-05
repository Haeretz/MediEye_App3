package kr.co.dgall.medieye_app3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.dgall.medieye_app3.handler.LoginFailureHandler;
import kr.co.dgall.medieye_app3.handler.LoginSuccessHandler;
import kr.co.dgall.medieye_app3.handler.LogoutHandler;
import kr.co.dgall.medieye_app3.handler.OAuth2LoginSuccessHandler;
import kr.co.dgall.medieye_app3.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	
	private final OAuth2LoginSuccessHandler oAuth2LoginsuccessHandler;
	private final CustomOAuth2UserService oAuth2UserService;
	private final LogoutHandler logoutHandler;
	private final LoginSuccessHandler loginSuccessHadler;
	private final LoginFailureHandler loginFailureHandler;	
	
	@Bean
	public PasswordEncoder passwordEncoder( ) {
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
				.antMatchers("/api/**", "/", "/userlogin","/join","/login","/joinuser").permitAll()
				.antMatchers("/loginSuccess").authenticated()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(loginSuccessHadler)
				.failureHandler(loginFailureHandler)
				.loginProcessingUrl("/userlogin")
			.and()
				.logout()
					.logoutSuccessUrl("/login")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 post로 인식해서 수행됨
					.addLogoutHandler(logoutHandler)              
					.deleteCookies("sessionId")
					.invalidateHttpSession(true)
					.clearAuthentication(true)
			.and()
	            .oauth2Login()
		            .loginPage("/login") // 로그인페이지 uri
		            .successHandler(oAuth2LoginsuccessHandler)
		            .userInfoEndpoint()
		            .userService(oAuth2UserService);
				
		return http.build();
	}
	
}
