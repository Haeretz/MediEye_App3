package kr.co.dgall.medieye_app3.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Notification {
	
	/** 공지사항 ID */
	private Integer noticeId;
	/** 공지사항 No */
	private String noticeNo ;
	/** 공지사항 타입 */
	private String noticeType;
	/** 공지사항 내용 */
	private String noticeMsg;
}