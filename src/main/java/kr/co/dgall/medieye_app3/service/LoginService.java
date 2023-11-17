package kr.co.dgall.medieye_app3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.mapper.SnsMemberMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.model.SnsMember;

@Service
public class LoginService {
	
	@Autowired
	private SnsMemberMapper snsMemberMapper;
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	
	/** 회원가입 체크 */
	public SnsMember memberCheck(SnsMember snsMember) {
		
		SnsMember resultMember = snsMemberMapper.getSnsMember(snsMember);
		
		if(resultMember == null) {
			snsMember.setUseYn("Y"); 
			snsMemberMapper.insertSnsMember(snsMember);
			return snsMember;
		}
		return resultMember;
	}
	
	/** 일반 로그인 */
	public MemberDoctor login(MemberDoctor memberDoctor) {
		
		MemberDoctor userInfo = memberDoctorMapper.getMemberDoctor(memberDoctor);
		System.out.println("userInfo password:" + userInfo.getPassword());
		if (userInfo != null) {
			System.out.println("userInfo:" + userInfo.getPassword());
			System.out.println("memberDoctor:" + memberDoctor.getPassword());
			if (!userInfo.getPassword().equals(memberDoctor.getPassword())) {
				// 로그인 실패 횟수 업데이트, 로그인 시도일시, 수정일시
				
				userInfo = null;
			} else {
				// 로그인 성공
				// 마지막 로그인 성공일시, 로그인 시도일시, 실패회수 초기화 업데이트, 수정일시
			}
		}		
		return userInfo;
	}

}
