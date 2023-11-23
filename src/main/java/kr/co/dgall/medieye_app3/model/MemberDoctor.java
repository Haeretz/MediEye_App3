package kr.co.dgall.medieye_app3.model;


import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	@NotBlank(message="이메일을 입력해주세요.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	/** 비밀번호 */
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password; 	
	/** 이름 */
	@NotBlank(message = "이름을 입력해주세요.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣]{2,10}$", message = "이름이 올바르지 않습니다.")
	private String username;
	/** 성별 */
	@NotBlank(message = "성별을 입력해주세요.") // 라디오 버튼(남,여)
	private String gender;
	/** 생년월일 */
	@NotBlank(message = "생년월일을 입력해주세요.")// 드롭다운(YYYY-MM-DD)
	private String birth;
	/** 병원 */
	@NotBlank(message = "병원을 입력해주세요.")
	@Pattern(regexp = "^[가-힣0-9A-Za-z]{2,20}$", message = "병원이름이 올바르지 않습니다.")
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
