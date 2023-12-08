package kr.co.dgall.medieye_app3.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.mapper.SnsMemberMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.model.SnsMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	@Autowired
	private SnsMemberMapper snsMemberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/** 회원가입 */
	public MemberDoctor join(MemberDoctor member, String snsId) {
		if(snsId == null) {
		
			member.setAutoLogin("N");
			member.setUseYn("Y");
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			memberDoctorMapper.insertMember(member);
			
//			MemberDoctor user = memberDoctorMapper.getMemberDoctor(member.getEmail());			
//			if(member.getEmail().equals(user.getEmail())){
//				return null;
//			}
			
		} else {
			String randomId = createDefaultEmail();
			member.setEmail(randomId);
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			memberDoctorMapper.insertMember(member);
		}
		
		if(snsId != null) {
			// 멤버테이블에 랜덤값으로 저장되는 계정을 찾아서 해당 memberid를 소셜멤버에 넣어주기
			// 소셜 로그인 연동 회원가입 시 memberId 소셜멤버에 넣어주기
			SnsMember snsMember = new SnsMember();
			if(member.getEmail().contains("test.com")) {
				snsMember.setEmail(null);
			} else {
			snsMember.setEmail(member.getEmail());
			}
			snsMember.setSnsId(snsId);
			SnsMember findSnsMember = snsMemberMapper.getSnsMember(snsMember);
			if(findSnsMember != null) {
				if(findSnsMember.getMemberId() == null) { 
					SnsMember updateSns = new SnsMember(); 
					updateSns.setMemberId(member.getMemberId());
					updateSns.setSnsId(findSnsMember.getSnsId());
					snsMemberMapper.updateSnsMember(updateSns); 
				} 
			}
		}
		log.info("member? : {}", member);
		return member;
	}


	// 랜덤Email 생성
	private String createDefaultEmail() {
		UUID uuid = UUID.randomUUID();
		String randomId = uuid.toString().replaceAll("-","").substring(0,8);
		String id = randomId + "@test.com";
		return id;
	}

	/** 유효성 검사 */
	public Map<String, String> validateHandler(Errors errors) {
		Map<String, String> valid = new HashMap<>();
		 for (FieldError error : errors.getFieldErrors()) {
		        String validKeyName = "valid_" + error.getField();
		        valid.put(validKeyName, error.getDefaultMessage());
		    }
		return valid;
	}

}
