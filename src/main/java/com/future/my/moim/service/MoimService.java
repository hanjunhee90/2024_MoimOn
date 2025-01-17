package com.future.my.moim.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.my.member.vo.InterestVO;
import com.future.my.moim.dao.IMoimDAO;
import com.future.my.moim.vo.MoimRoomsVO;
import com.future.my.moim.vo.MoimVO;

@Service
public class MoimService {
	
	@Autowired
	IMoimDAO moimDao;
	
	// 모임 생성시 보여줄 관심사 리스트
	public ArrayList<InterestVO> selectInterestName() {
		return moimDao.getInterestName();
	}
	
	@Transactional
	public void createMoim(MoimVO moim, MoimRoomsVO room) {
		moimDao.insertMoim(moim);
		int moimId = moim.getMoimId();
		//System.out.println("moimId"+moimId);
		room.setMoimId(moimId);
		//System.out.println(room);
		moimDao.insertMoimRoom(room);
	}
	
	// 모임 생성 및 참가 들어갈 회원, 모임정보 저장 메소드
	public void joinMoim(MoimRoomsVO roomvo) {
		moimDao.insertMoimRoom(roomvo);
	}
	
	public List<MoimVO> getMoimList(int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return moimDao.getMoimList(params);
    }
	
	public MoimVO getMoimById(int moimId) {
	    return moimDao.getMoimById(moimId);
	}
	
}
