package kr.co.dgall.medieye_app3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dgall.medieye_app3.mapper.IntroMapper;
import kr.co.dgall.medieye_app3.model.CheckVersion;
import kr.co.dgall.medieye_app3.model.Notification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntroService {
	
	private final IntroMapper introMapper;
	
	/** 서버 버전 정보 조회*/
	public CheckVersion getVersion() {
		return introMapper.getCheckVersion();
	}
	
	/** 버전 정보 체크
	 * currentVer : 기기 현재버전
	 * lastestVer : 서버 최신버전
	 * */
	public Boolean checkVersion(String currentVer) {
		CheckVersion lastestVer = introMapper.getCheckVersion();
		if(!lastestVer.getLastestVer().equals(currentVer)){
			//버전업그레이드 여부
			return true; 
		}
		return false;
	}
	
	/** 공지사항 조회*/
	public List<Notification> getNotification() {
		return introMapper.getNotificationList();
	}
	
}
