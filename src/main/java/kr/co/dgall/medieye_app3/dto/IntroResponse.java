package kr.co.dgall.medieye_app3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroResponse {
	
	// 공지사항 Rows
	private String notices;
	// 최신버전
	private String lastestVer;
	// 강제 업데이트 여부
	private String isForce;

}
