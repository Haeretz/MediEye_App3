package kr.co.dgall.medieye_app3.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dgall.medieye_app3.dto.ApiResponse;
import kr.co.dgall.medieye_app3.dto.IntroRequest;
import kr.co.dgall.medieye_app3.model.CheckVersion;
import kr.co.dgall.medieye_app3.model.Notification;
import kr.co.dgall.medieye_app3.service.IntroService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class IntroController {
	
	@Autowired
	private IntroService introService;
	
	/**
	 * REST API - 인트로 API
	 * getLastVer : 서버 최신버전
	 */
	@RequestMapping(value="/intro")
	public ApiResponse<?> getIntro(@RequestBody IntroRequest request){
		if(request.getDeviceId() == null && request.getCurruentVer() == null && request.getPlatform() == null){
			return ApiResponse.createError("파라미터 값이 올바르지 않습니다.");
		}
		CheckVersion getVer = introService.getVersion();
		List<Notification> notification = introService.getNotification();
		Boolean checkVer = introService.checkVersion(request.getCurruentVer());
		log.info("request: {}", request.getDeviceId());
		HashMap<String,Object> map = new HashMap<>();
		map.put("notice", notification);
		map.put("lastest_ver", getVer.getLastestVer());
		map.put("is_force", getVer.getIsForce());
		map.put("check_ver", checkVer);
		
		return ApiResponse.createSuccess(map, "");
	}		
	
	
}
