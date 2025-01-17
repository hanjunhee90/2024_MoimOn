package com.future.my.moim.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.future.my.member.vo.InterestVO;
import com.future.my.moim.service.MoimService;
import com.future.my.moim.vo.MoimRoomsVO;
import com.future.my.moim.vo.MoimVO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/moim")
@Controller
public class MoimController {
	
	@Autowired
	MoimService moimService;
	
	@Autowired
    BoardService boardService;
	
	// 파일저장 경로
	@Value("${project.upload.path}")
    private String uploadPath;
	
	@GetMapping("/moimForm")
	public String moimView(Model model) {
		
		ArrayList<InterestVO> ctgList = moimService.selectInterestName();
		//System.out.println(ctgList);
		model.addAttribute("ctgList",ctgList);
		return "moim/moimForm";
		
	}
	


    // 모임생성
    @PostMapping("/createMoim")
    public ResponseEntity<String> createMoim(@ModelAttribute MoimVO moim,
                                             Model model, BoardVO board,
                                             @RequestPart(value = "images", required = false) MultipartFile[] images) {
    	System.out.println(moim);
    	List<BoardFileVO> fileVOList = new ArrayList<>();
    	System.out.println(moim.getMoimDt());
    	MoimRoomsVO room = new MoimRoomsVO();
    	room.setApprovalStatus(moim.getMoimConfirm());
    	
    	room.setMemId(board.getBoardWriter());
    	room.setRoomRole("모임장");
    	try {
            if (images != null) {
                for (MultipartFile image : images) {
                    if (!image.isEmpty()) {
                        String savedFileName = FileUploadUtil.saveFile(image, uploadPath);
                        System.out.println("저장된 파일 이름: " + savedFileName);
                        BoardFileVO filevo = new BoardFileVO();
                        filevo.setFileName(savedFileName);
                        filevo.setFilePath(uploadPath);
                        fileVOList.add(filevo);
                    }
                }
            }
            moimService.createMoim(moim, room);
            boardService.insertBoard(board, fileVOList);
            
            // 다른 비즈니스 로직 처리 (예: moimvo, board 등 데이터 저장)
            return ResponseEntity.ok("모임 생성 성공");
        } catch (Exception e) {
            System.out.println("오류: " + e.getMessage());
            e.printStackTrace(); // 예외 스택 트레이스 출력
            return ResponseEntity.status(500).body("파일 저장 중 오류가 발생했습니다.");
        }
    }
    
    @GetMapping("/{moimId}")
    public String getMoimDetail(@PathVariable("moimId") int moimId, Model model) {
        MoimVO moim = moimService.getMoimById(moimId);
        String content =  moim.getBoardContent().replaceAll("<img[^>]*>", "");
        moim.setBoardContent(content);
        model.addAttribute("moim", moim);
        return "moim/detail";
    }

}
