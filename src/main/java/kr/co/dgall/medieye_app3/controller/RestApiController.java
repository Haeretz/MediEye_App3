package kr.co.dgall.medieye_app3.controller;

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
	 * REST API 샘플 - 맴버닥터 조회
	 * @param memberDoctor
	 */
	@GetMapping(value="/sample/member")
	public ApiResponse<MemberDoctor> getMemberDoctor(MemberDoctor memberDoctor) throws Exception {
		
		MemberDoctor reslt = sampleService.getMemberDoctor(memberDoctor);
		
		return ApiResponse.createSuccess(reslt, "asdasf");
	}
}
