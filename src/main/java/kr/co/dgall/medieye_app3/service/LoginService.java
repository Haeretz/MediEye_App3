package kr.co.dgall.medieye_app3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	/** 일반 로그인 */
	public MemberDoctor login(String email) {
		return memberDoctorMapper.getMemberDoctor(email);
	}
	
	public MemberDoctor getUserInfo(String email) {
		MemberDoctor userInfo = memberDoctorMapper.getMemberDoctor(email);
		return userInfo;
	}
//	public MemberDoctor login(MemberDoctor memberDoctor) {
//		
//		MemberDoctor userInfo = memberDoctorMapper.getMemberDoctor(memberDoctor);
//		System.out.println("userInfo password:" + userInfo.getPassword());
//		
		// 로그인 ver_1
//		if (userInfo != null) {
//			System.out.println("userInfo:" + userInfo.getPassword()); 
//			System.out.println("memberDoctor:" + memberDoctor.getPassword());
//			
//			if (!userInfo.getPassword().equals(memberDoctor.getPassword())) {
//				// 로그인 실패 횟수 업데이트, 로그인 시도일시, 수정일시
//				userInfo = null;
//			} else {
//				// 로그인 성공
//				/** TODO 마지막 성공일시 업데이트 */				
//				/** TODO 로그인 시도 일시 업데이트 */
//				/** TODO 로그인 실패 횟수 초기화 */
//				// 마지막 로그인 성공일시, 로그인 시도일시, 실패회수 초기화 업데이트, 수정일시, myPage 접근권한
//			}
//		}	
//		
//		return userInfo;
//	}
	

}
