package kr.co.dgall.medieye_app3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.mapper.MemberDoctorMapper;
import kr.co.dgall.medieye_app3.model.MemberDoctor;

/**
 * 샘플 서비스
 */
@Service
public class SampleService {
	
	@Autowired
	private MemberDoctorMapper memberDoctorMapper;
	
	public MemberDoctor getMemberDoctor(MemberDoctor memberDoctor) throws Exception {
		return memberDoctorMapper.getMemberDoctor(memberDoctor.getEmail());
	}
	
	public List<MemberDoctor> getMemberList() throws Exception{
		List<MemberDoctor> memberList = memberDoctorMapper.getMemberDoctorList();
		return memberList;
	}
}
