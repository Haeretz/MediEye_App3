package kr.co.dgall.medieye_app3.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/** 회원가입 */
	public MemberDoctor join(MemberDoctor member) {
		
			member.setAutoLogin("N");
			member.setUseYn("Y");
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			memberDoctorMapper.insertMember(member);
			
		return member;
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

	public Boolean checkId(String email) {
		MemberDoctor findInfo = memberDoctorMapper.getMemberDoctor(email);
		if(findInfo != null) {
			return false;
		}
		return true;		
	}

}
