package kr.co.dgall.medieye_app3.model;


import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class MemberDoctor {
	
	/** 멤버 ID */
	private Integer memberId; 	
	/** Email */
	private String email;
	/** 비밀번호 */
	private String password; 	
	/** 이름 */
	private String username;
	/** 성별 */
	private String gender;
	/** 생년월일 */
	private String birth;
	/** 병원 */
	private String hospital;
	/** 사용여부 YN */
	private String useYn;
	/** 마지막 로그인 성공 일시 */
	private Timestamp lastLoginTime;
	/** 로그인 시도 일시 */
	private Timestamp tryLogin;
	/** 로그인 실패 횟수 */
	private int loginFail;
	/** PINCODE */
	private int pinCode;
	/** 자동로그인 여부 */
	private String autoLogin;
	/** 수정일시 */
	private Timestamp updateDate;
	/** 생성일시 */
	private Timestamp createDate;
}
