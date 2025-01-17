package com.future.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.moim.service.MoimService;
import com.future.my.moim.vo.MoimVO;

/**
 *  localhost:8080/ 경로로 들어가면 index.html로 연결
 */
@Controller
public class HomeController {

	@Autowired
	MoimService moimService;
	
	@GetMapping("/") 
	public String index(@RequestParam(value = "offset", defaultValue = "0") int offset,
		    @RequestParam(value = "limit", defaultValue = "10") int limit, Model model) {
		List<MoimVO> moimList = moimService.getMoimList(offset, limit);
	    model.addAttribute("moimList", moimList);
		return "index"; 
	}

	@GetMapping("test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/testEdit")
	public ResponseEntity<String> test(@RequestPart(value = "images", required = false) MultipartFile[] images, @RequestParam("boardContent") String boardContent) {
		System.out.println("일단옴");
		System.out.println(boardContent);
		if (images != null && images.length > 0) {
	        for (MultipartFile image : images) {
	            if (!image.isEmpty()) {
	                String originalFilename = image.getOriginalFilename();
	                System.out.println("파일 이름: " + originalFilename);
	            }
	        }
	    }
	    return ResponseEntity.ok("이미지 업로드 성공");
	}

}
