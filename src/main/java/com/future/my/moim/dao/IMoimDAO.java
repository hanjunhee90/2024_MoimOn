package com.future.my.moim.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.member.vo.InterestVO;
import com.future.my.moim.vo.MoimRoomsVO;
import com.future.my.moim.vo.MoimVO;

@Mapper
public interface IMoimDAO {
	
	// 모임 설정시 보여줄 관심사이름
	public ArrayList<InterestVO> getInterestName();
	
	public void insertMoim(MoimVO moim);
	
	// 모임 생성 및 참가시 저장시켜줌
	public void insertMoimRoom(MoimRoomsVO room);
	
	public List<MoimVO> getMoimList(Map<String, Object> params);
	
	public MoimVO getMoimById(int moimId);

}
