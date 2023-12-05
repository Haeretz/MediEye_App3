package kr.co.dgall.medieye_app3.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroRequest {
	
	@NotEmpty
	private String platform;
	@NotEmpty
	private String deviceId;
	@NotEmpty
	private String curruentVer;

}
