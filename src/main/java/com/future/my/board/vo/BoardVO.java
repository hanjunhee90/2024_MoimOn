package com.future.my.board.vo;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boardId;               /* 게시판 id(시퀀스로) SEQ_BOARD */
	private String boardCategory;      /* 게시판카테고리 (100-모임, 200-클래스, 300-자유게시판) */
	private String boardWriter;        /* 작성자 memId */
	private String boardTitle;         /* 글제목 */
	private Object boardContent;       /* 글내용 CLOB */
	private String boardCreate;        /* 생성날짜 */
	private String boardModify;        /* 수정닐찌 */
	private int boardHit;              /* 조회수 */
	private int boardLike;             /* 좋아요 */
	private Object boardDelete;        /* 삭제여부 기본 N 삭제시 Y로*/
	

}
