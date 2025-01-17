package com.future.my.member.vo;

import lombok.Data;

/**
 * 회원 vo
 */
@Data
public class MemberVO {
	
	private String memId;        /* 회원아이디 */
	private String memPass;      /* 회원비밀번호 */
	private String memName;      /* 회원이름 */
	private String memSocial;    /* 쇼셜로그인id */
	private String memEmail;     /* 이메일 */
	private String memMbti;      /* MBTI */
	private String memJob;       /* 직업 */
	private String memGender;    /* 성별 */
	private int memTrust;        /* 신뢰도 */
	private String memBirth;     /* 생년월일 */
	private String profileImg;   /* 프로필이미지 */
	private String createDt;     /* 회원가입날짜 */
	private String updateDt;     /* 정보수정날짜 */
	private String useYn;        /* 탈퇴여부 */
	private String memType;      /* 회원 종류 bagic,kakao,naver,google*/
	private String memAddr1;     /* 동네설정1 */
	private String memAddr2;     /* 동네설정2 */
	private String memAddr3;     /* 동네설정3 */

}
