package kr.co.dgall.medieye_app3.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.dgall.medieye_app3.model.MemberDoctor;

@Mapper
public interface MemberDoctorMapper {
	
	/** 의사 리스트 조회 */
	public MemberDoctor getMemberDoctor(MemberDoctor memberDoctor);
	
	/** 의사 회원 가입 */
	public int insertMember(MemberDoctor memberDoctor);
	
}
