package kr.co.dgall.medieye_app3.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SnsMember {
	
	/** SNS ID(PK)*/
	private String snsId;
	/** Email */
	private String email;
	/** SNS 타입 */
	private String snsType; 	
	/** 연동된 멤버 ID */
	private Integer memberId; 	
	/** 사용여부 YN */
	private String useYn; 	
	/** 수정일시 */
	private Timestamp updateDate;
	/** 생성일시 */
	private Timestamp createDate;
}
