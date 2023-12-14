package kr.co.dgall.medieye_app3.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.model.MemberDoctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityService implements UserDetailsService{
	
	private final LoginService loginService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		MemberDoctor userInfo = loginService.login(email);
		log.info("userInfo email: {}", email);
		
		return User.builder()
				.username(userInfo.getEmail())
				.password(userInfo.getPassword())
				.roles("USER")
				.build();
	}

}
