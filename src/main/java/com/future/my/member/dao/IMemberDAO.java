package com.future.my.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.member.vo.InterestVO;
import com.future.my.member.vo.MbtiVO;
import com.future.my.member.vo.MemberVO;

@Mapper
public interface IMemberDAO {
	
    /**
     * 회원 테이블에 새로운 회원 추가
     *
     * @param member 새로운 회원 정보
     */
    // 회원 가입 메서드
    public void insertMember(MemberVO member);

    // 아이디 중복 체크 메서드
    public int checkId(String memId);
    
    // 회원 아이디로 회원 조회
    public MemberVO findById(String memId);

    /**
     * 관심 카테고리 및 관심사 리스트 조회
     *
     * @return 관심사 목록
     */
	
	public List<InterestVO> selectInterests(List<Integer> categoryIds);
	
	//mbti 리스트
	public ArrayList<MbtiVO> mbtiList();
	
	// 관심사 저장
	public void insertMemberInterest(String memId, int itrId);
	
	// 주소 저장
	public void insertMemberAddr(MemberVO member);
	
	// 프로필 이미지 저장
	public void updateProfile(MemberVO member);

}
