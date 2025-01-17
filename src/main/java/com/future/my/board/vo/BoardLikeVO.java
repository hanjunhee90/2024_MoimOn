package com.future.my.board.vo;

import lombok.Data;

@Data
public class BoardLikeVO {
	
	private int likeId;         /* 좋아요id SEQ_LIKE */
	private int boardId;        /* 게시판ID */
	private String likeMember;  /* 좋아요누른회원 */
	private String likeDate;    /* 좋아요 한 시간 */

}
