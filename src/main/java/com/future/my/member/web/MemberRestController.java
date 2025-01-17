package com.future.my.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.future.my.member.service.MemberService;

@RestController
public class MemberRestController {
	
	@Autowired
	MemberService memService;
	
    // 아이디 중복여부 체크
    @GetMapping("/api/checkid")
    public String checkId(@RequestParam String memId) {
    	boolean isDuplicate = memService.findById(memId);
    	System.out.println(isDuplicate+memId+"??");
    	return isDuplicate ? "available" : "duplicate";    // true면 카운트값이 0이니까 사용가능
    }

}
