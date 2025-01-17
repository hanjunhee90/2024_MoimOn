package com.future.my.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.my.board.dao.IBoardDAO;
import com.future.my.board.vo.BoardFileVO;
import com.future.my.board.vo.BoardVO;

@Service
public class BoardService {
	
	 	@Autowired
	 	IBoardDAO boardDao;

	 	public ArrayList<BoardVO> getAllBoards(int page) {
	 	    int limit = 10; // 페이지당 표시할 게시글 수
	 	    int offset = (page - 1) * limit; // 시작 위치 계산
	 	    return boardDao.getAllBoards(offset, limit);
	 	}
	    public BoardVO getBoardById(int boardId) {
	    	boardDao.increaseBoardHit(boardId);
	        return boardDao.getBoardById(boardId);
	    }

	    @Transactional
	    public void insertBoard(BoardVO board, List<BoardFileVO> files) {
	    	 // 1. 게시글 저장
	    	boardDao.insertBoard(board);
	    	System.out.println("여기와??");
	    	 // 2. 게시글 ID 가져온 후 파일 저장
	        int boardId = board.getBoardId(); // 자동 생성된 boardId 가져오기
	        System.out.println("boardId는 "+boardId);
	        if (files != null && !files.isEmpty()) {
	            for (BoardFileVO file : files) {
	                file.setBoardId(boardId); // 생성된 boardId를 파일에 설정
	                boardDao.insertFile(file);
	            }
	        }
	    }
	    

	    public void updateBoard(BoardVO board) {
	    	boardDao.updateBoard(board);
	    }

	    public void deleteBoard(int boardId) {
	    	boardDao.deleteBoard(boardId);
	    }

	    public void increaseBoardHit(int boardId) {
	    	boardDao.increaseBoardHit(boardId);
	    }

}
