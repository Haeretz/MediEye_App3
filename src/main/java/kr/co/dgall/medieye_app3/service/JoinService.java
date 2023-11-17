package kr.co.dgall.medieye_app3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.mapper.SnsMemberMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;

@Service
public class JoinService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	@Autowired
	private SnsMemberMapper snsMemberMapper; 
	
	/** 회원가입 */
	public MemberDoctor join(MemberDoctor member) {
		member.setAutoLogin("N");
		member.setUseYn("Y");
		memberDoctorMapper.insertMember(member);
		
		/*
		 * // 소셜 로그인 연동 회원가입 시 memberId 소셜멤버에 넣어주기 SnsMember snsMember = new
		 * SnsMember(); snsMember.setEmail(member.getEmail()); SnsMember findSnsMember =
		 * snsMemberMapper.getSnsMember(snsMember); if(findSnsMember != null) {
		 * if(findSnsMember.getMemberId() == null) { SnsMember updateSns = new
		 * SnsMember(); updateSns.setMemberId(member.getMemberId());
		 * updateSns.setSnsId(findSnsMember.getSnsId());
		 * snsMemberMapper.updateSnsMember(updateSns); } }
		 */
		return member;
	}
}
