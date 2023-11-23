package kr.co.dgall.medieye_app3.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.dgall.medieye_app3.model.MemberDoctor;

@Mapper
public interface MemberDoctorMapper {
	
	/** 의사 리스트 조회 */
	public MemberDoctor getMemberDoctorList(MemberDoctor memberDoctor);
	
	/** 의사 단일 조회 */
	public MemberDoctor getMemberDoctor(String email);
	
	/** 의사 회원 가입 */
	public int insertMember(MemberDoctor memberDoctor);

	/** 로그인 성공시 업데이트 */
	public int updateLoginSuccessMemberDoctor(MemberDoctor memberDoctor);

	/** 로그인 실패시 업데이트 */
	public int updateLoginFailMemberDoctor(String email);
	
}
