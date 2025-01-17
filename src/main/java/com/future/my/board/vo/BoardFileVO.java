package com.future.my.board.vo;

import lombok.Data;

@Data
public class BoardFileVO {
	
	private int fileId;               /* 파일테이블 기본키 SEQ_FILE */
	private int boardId;              /* 게시판아이디 */
	private String filePath;          /* 파일저장경로 */
	private String fileName;          /* 파일이름 */
	private String fileUploadDate;    /* 파일업로드날짜 */
	

}
