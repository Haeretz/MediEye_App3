package kr.co.dgall.medieye_app3.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.mapper.SnsMemberMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.model.SnsMember;

@Service
public class JoinService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	@Autowired
	private SnsMemberMapper snsMemberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/** 회원가입 */
	public MemberDoctor join(MemberDoctor member) {
		member.setAutoLogin("N");
		member.setUseYn("Y");
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberDoctorMapper.insertMember(member);
		
		
		  // 소셜 로그인 연동 회원가입 시 memberId 소셜멤버에 넣어주기 
		SnsMember snsMember = new
		SnsMember(); snsMember.setEmail(member.getEmail()); SnsMember findSnsMember =
		snsMemberMapper.getSnsMember(snsMember); if(findSnsMember != null) {
		if(findSnsMember.getMemberId() == null) { SnsMember updateSns = new
				SnsMember(); updateSns.setMemberId(member.getMemberId());
				updateSns.setSnsId(findSnsMember.getSnsId());
				snsMemberMapper.updateSnsMember(updateSns); } }
	 
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
	
}
