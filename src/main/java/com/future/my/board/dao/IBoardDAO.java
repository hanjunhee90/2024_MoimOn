package com.future.my.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.future.my.board.vo.BoardFileVO;
import com.future.my.board.vo.BoardVO;

@Mapper
public interface IBoardDAO {
	
	// 게시글 목록 조회
    public ArrayList<BoardVO> getAllBoards(@Param("offset") int offset, @Param("limit") int limit);

    // 게시글 상세 조회
    public BoardVO getBoardById(@Param("boardId") int boardId);

    // 게시글 작성
    public void insertBoard(BoardVO board);
    
    // 파일 저장
    public void insertFile(BoardFileVO filevo);

    // 게시글 수정
    public void updateBoard(BoardVO board);

    // 게시글 삭제
    public void deleteBoard(@Param("boardId") int boardId);

    // 게시글 조회수 증가
    public void increaseBoardHit(@Param("boardId") int boardId);

}
