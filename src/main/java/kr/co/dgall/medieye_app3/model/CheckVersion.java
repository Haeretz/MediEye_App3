package kr.co.dgall.medieye_app3.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CheckVersion {
	
	/** 버전 ID */
	private Integer versionId;
	/** 현재 버전 */
	private String currentVer;
	/** 강제 설치 여부 */
	private String isForce;
	/** 최신 버전 */
	private String lastestVer;
}