package kr.co.dgall.medieye_app3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.dgall.medieye_app3.model.CheckVersion;
import kr.co.dgall.medieye_app3.model.Notification;

@Mapper
public interface IntroMapper {
	
	/** 버전 조회 */
	public CheckVersion getCheckVersion();
	
	/** 공지사항 조회 */
	public List<Notification> getNotificationList();
}
