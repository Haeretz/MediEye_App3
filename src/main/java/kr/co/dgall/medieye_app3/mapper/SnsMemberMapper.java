package kr.co.dgall.medieye_app3.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.dgall.medieye_app3.model.SnsMember;

@Mapper
public interface SnsMemberMapper {
	
	/** SNS ID 유무 조회 */
	public SnsMember getSnsMember(SnsMember snsMember);
	
	/** 소셜멤버 insert */
	public int insertSnsMember(SnsMember snsMember);
	
	/** 소셜멤버 update */
	public int updateSnsMember(SnsMember snsMember);
	
}
