package kr.co.dgall.medieye_app3.model;

import java.security.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class LoginLog {
	
	/** Email */
	private String email;
	/** 로그인 유형 ex)자사, google... */
	private String loginType;
	/** User Agent */
	private String userAgent;
	/** 디바이스 id */
	private String deviceId;
	/** Ip */
	private String ip;
	/** 로그아웃 여부 YN */
	private String logoutYn;
	/** 성공 여부 */
	private String loginSuccessYn;
	/** 생성일시 */
	private Timestamp createDate;
}
