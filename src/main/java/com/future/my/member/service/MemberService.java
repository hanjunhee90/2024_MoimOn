package com.future.my.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.future.my.member.dao.IMemberDAO;
import com.future.my.member.vo.InterestVO;
import com.future.my.member.vo.MbtiVO;
import com.future.my.member.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	IMemberDAO memDao;

	
	@Autowired private PasswordEncoder passwordEncoder;
	  
	public String encryptPassword(String rawPassword) {
 
		return passwordEncoder.encode(rawPassword); // 비밀번호 암호화 }
	}

	// 회원가입 시 비밀번호 암호화 후 저장
	public boolean registerMember(MemberVO member) {
		try {
	        String encodedPassword = passwordEncoder.encode(member.getMemPass());
	        member.setMemPass(encodedPassword);  // 암호화된 비밀번호로 설정
	        memDao.insertMember(member);  // DB에 저장
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public MemberVO memInfo(String memId) {
		return memDao.findById(memId);
	}

	// 아이디 중복 체크
	public boolean findById(String memId) {
		return memDao.checkId(memId) == 0; // 0 일경우 id써도됨
	}

	// 비밀번호 확인 (암호화된 비밀번호와 비교)
	public boolean checkPassword(String memId, String rawPassword) {
		MemberVO member = memDao.findById(memId);
		// true 이면 비밀번호 일치
		return member != null && passwordEncoder.matches(rawPassword, member.getMemPass());
	}

	// 관심사 관련 정보
	public List<InterestVO> getInterestsByCategoryIds(List<Integer> categoryIds) {
		return memDao.selectInterests(categoryIds);
	}
	
	// mbti 리스트 호출
	public ArrayList<MbtiVO> mbtiList() {
		
		return memDao.mbtiList();
	}
	
	// 관심사 업데이트
	public void saveMemberInterests(String memId, List<Integer> interests) {
        for (Integer itrId : interests) {
            if (itrId != null) { // 선택된 항목만 저장
                memDao.insertMemberInterest(memId, itrId);
            }
        }
    }
	
	// 관심주소 업데이트
	public void updateMemberAddr(MemberVO member) {
		if(member != null) {
			memDao.insertMemberAddr(member);
		}
		
	}
	
	// 프로필 수정
	public void savaProfile(MemberVO member) {
		memDao.updateProfile(member);
	}

}
