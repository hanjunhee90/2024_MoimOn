package com.future.my.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.board.service.BoardService;
import com.future.my.board.vo.BoardFileVO;
import com.future.my.board.vo.BoardVO;
import com.future.my.config.FileUploadUtil;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	// 파일저장 경로
	@Value("${project.upload.path}")
    private String uploadPath;
	
	@Autowired
    BoardService boardService;

    // 게시글 목록 조회
    @GetMapping("/list")
    public String listBoards(@RequestParam(defaultValue = "1") int page, Model model) {
        ArrayList<BoardVO> boardList = boardService.getAllBoards(page);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/{boardId}")
    public String viewBoard(@PathVariable int boardId, Model model) {
        BoardVO board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "board/view";
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/new")
    public String newBoardForm(Model model) {
        model.addAttribute("board", new BoardVO());
        return "board/new";
    }
    // 게시글 저장
    @PostMapping("/new")
    public ResponseEntity<String> createBoard(@RequestPart(value = "images", required = false) MultipartFile[] images,
    							@ModelAttribute BoardVO board) {
    	List<BoardFileVO> fileVOList = new ArrayList<>();
    	System.out.println(board);
    	try {
            if (images != null && images.length > 0) {
                for (MultipartFile image : images) {
                    if (!image.isEmpty()) {
                        String savedFileName = FileUploadUtil.saveFile(image,uploadPath);
                        System.out.println("저장된 파일 이름: " + savedFileName);
                        BoardFileVO filevo = new BoardFileVO();
                        filevo.setFileName(savedFileName);
                        filevo.setFilePath(uploadPath);
                        fileVOList.add(filevo);
                    }
                }
            }
            boardService.insertBoard(board, fileVOList);
            
            return ResponseEntity.ok("모임 생성 성공");
        } catch (Exception e) {
            System.out.println("오류: " + e.getMessage());
            return ResponseEntity.status(500).body("파일 저장 중 오류가 발생했습니다.");
        }
    	// 파일 불러올땐 th:src="@{'/uploads/' + ${file.fileName}}" 이런식으로!!!
        
    }

    // 게시글 수정 페이지 이동
    @GetMapping("/edit/{boardId}")
    public String editBoardForm(@PathVariable int boardId, Model model) {
        BoardVO board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "board/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/edit")
    public String updateBoard(@ModelAttribute BoardVO board) {
        boardService.updateBoard(board);
        return "redirect:/board/list";
    }

    // 게시글 삭제 처리
    @GetMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board/list";
    }

}
