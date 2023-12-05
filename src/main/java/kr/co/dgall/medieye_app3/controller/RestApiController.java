package kr.co.dgall.medieye_app3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dgall.medieye_app3.dto.ApiResponse;
import kr.co.dgall.medieye_app3.model.MemberDoctor;
import kr.co.dgall.medieye_app3.service.SampleService;

@RestController
@RequestMapping("/api")
public class RestApiController {
	
	@Autowired
	private SampleService sampleService;

	/**
	 * REST API - 맴버닥터 조회
	 * @param memberDoctor
	 */
	@GetMapping(value="/member")
	public ApiResponse<MemberDoctor> getMember(MemberDoctor memberDoctor) throws Exception {
		MemberDoctor result = sampleService.getMemberDoctor(memberDoctor);
		return ApiResponse.createSuccess(result, "");
	}
	
	/**
	 * REST API - 맴버닥터 리스트조회
	 */
	@GetMapping(value="/members")
	public ApiResponse<List<MemberDoctor>> getMemberList(MemberDoctor memberDoctor) throws Exception{
		List<MemberDoctor> result = sampleService.getMemberList(); 
		return ApiResponse.createSuccess(result, "");
	}
	
}
