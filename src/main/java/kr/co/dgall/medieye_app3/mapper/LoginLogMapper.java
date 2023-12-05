package kr.co.dgall.medieye_app3.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.dgall.medieye_app3.model.LoginLog;

@Mapper
public interface LoginLogMapper {
	
	/** 로그인이력 Insert */
	public int insertLog(LoginLog loginLog);
	
	/** 로그인이력 update */
	public int updateLog(LoginLog loginLog);
}
