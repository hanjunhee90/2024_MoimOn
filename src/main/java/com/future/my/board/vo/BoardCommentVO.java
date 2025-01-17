package com.future.my.board.vo;

import lombok.Data;

@Data
public class BoardCommentVO {
	
	private int commentId;           /* 댓글 id SEQ_COMMENT */
	private int boardId;             /* 게시판id */
	private String commentMem;       /* 작성자 memId */
	private String commentContent;   /* 댓글내용 */
	private String commentCreate;    /* 댓글작성일 */
	
}
