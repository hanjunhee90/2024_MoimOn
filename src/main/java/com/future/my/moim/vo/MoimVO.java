package com.future.my.moim.vo;

import lombok.Data;

// 모임 테이블
@Data
public class MoimVO {
	
	private int moimId;                 /* 모임기본키 */
	private String moimInterest;        /* 모임관심사설정 ctgId(카테고리기본키) */
	private String moimCtg;             /* 모임종류(모임인지 클래스인지) */
	private int moimMinple;             /* 최소인원 */
	private int moimMaxple;             /* 최대인원 */
	private int moimMinage;             /* 최소나이 */
	private int moimMaxage;             /* 최대나이 */
	private int moimFee;                /* 모임비 */
	private String createDt;            /* 모임생성날짜 */
	private String moimDt;              /* 만남날짜 */
	private String moimPlace;           /* 모임장소 */
	private String moimConfirm;         /* 승인제or즉시참여 여부 */
	private String moimType;            /* 1회성인지 지속성인지 */
	private String moimFeedetail1;		/* 모임비상세1 */
	private String moimFeedetail2;		/* 모임비상세2 */
	private String moimFeedetail3;		/* 모임비상세3 */
	private String moimFeedetail4;		/* 모임비상세4 */
	
	private String fileName;
	private String memId;
	private String memName;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	
	

}
