package com.future.my.moim.vo;

import lombok.Data;

@Data
public class MoimRoomsVO {
	
	private int moimRoomId;           /* 기본키 */
	private int moimId;               /* 모임ID 외래키 */
	private String memId;             /* 회원ID */
	private String roomRole;          /* 모임에서의 역할(모임장,참가자) */
	private String approvalStatus;    /* 승인여부(모임이 승인제일 경우 모임장이 승인) */
	private String joinDt;            /* 참가신청한 날짜 */
	

}
