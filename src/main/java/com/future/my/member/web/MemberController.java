package com.future.my.member.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.future.my.config.FileUploadUtil;
import com.future.my.member.service.MemberService;
import com.future.my.member.vo.InterestVO;
import com.future.my.member.vo.MbtiVO;
import com.future.my.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	// 파일저장 경로
	@Value("${project.upload.path}")
    private String uploadPath;
    
    @Autowired
    private MemberService memService;

    // 기본 프로필 이미지 파일명
    private static final String DEFAULT_PROFILE_IMAGE = "bagic.jpg";
    /**
     * 회원 가입 폼 페이지로 이동
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
    	ArrayList<MbtiVO> mbtiList = memService.mbtiList();
		model.addAttribute("mbtiList", mbtiList);
        return "members/register";  // 회원 가입 폼 템플릿 경로
    }

    // 회원 가입 처리
    @PostMapping("/register")
    public String registerMember(@ModelAttribute MemberVO member, RedirectAttributes redirectAttributes, HttpSession session) {
        boolean isRegistered = memService.registerMember(member);
        // 기본 프로필 이미지 설정
        
        member.setProfileImg(DEFAULT_PROFILE_IMAGE); // 기본 이미지 경로 저장
        
        if (isRegistered) {
            // 가입이 성공하면 세션에 사용자 정보 저장
            session.setAttribute("members", member); // "session.members"는 세션의 사용자 정보 키

            redirectAttributes.addFlashAttribute("message", "회원 가입이 성공적으로 완료되었습니다.");
            return "redirect:/";  // 홈 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 가입 중 오류가 발생했습니다.");
            return "/members/register";  // 회원 가입 페이지로 리다이렉트
        }
    }

    /**
     * 비밀번호 일치 확인 (로그인 처리 시 사용)
     */
    @PostMapping("/join")
    public String login(@RequestParam("memId") String memId,
                        @RequestParam("memPass") String memPass,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        System.out.println("로그인 시도 - ID: " + memId + ", 비밀번호: " + memPass);	
        boolean isPasswordCorrect = memService.checkPassword(memId, memPass);
        MemberVO members = memService.memInfo(memId);
        System.out.println(members);
        if (isPasswordCorrect) {
            // 세션에 사용자 정보를 저장
            session.setAttribute("members", members);
            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            return "redirect:/";  // 홈 페이지로 리다이렉트
        } else {
            // 로그인 실패 시 메시지와 로그인 모달 표시 플래그 추가
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 틀립니다.");
            
            return "redirect:/";  // 홈 페이지로 리다이렉트
        }
    }
    // 회원정보 수정
    @GetMapping("/meminfo")
    public String memInfo() {
    	
    	return "members/meminfo";
    }
    
    @GetMapping("/modify")
    public String modify() {
    	
    	return "members/modify";
    }
    
    @PostMapping("/updateProfile")
    public String updateProfile(MultipartFile profileImg, RedirectAttributes redirectAttributes, 
    							HttpSession session, Model model) {
    	//System.out.println(profileImg);
    	String memId = (String)session.getAttribute("memId");
    	MemberVO member = (MemberVO) session.getAttribute("members");
        if (profileImg != null && !profileImg.isEmpty()) {
            try {
                // FileUploadUtil 클래스 사용
                String savedFileName = FileUploadUtil.saveFile(profileImg, uploadPath);
                member.setProfileImg(savedFileName);
                
                //System.out.println(member);
                memService.savaProfile(member);
                // 저장된 파일 경로를 뷰로 전달 (나중에 DB에 저장 가능)
                redirectAttributes.addFlashAttribute("profileImgPath", "/uploads/" + savedFileName);
            } catch (RuntimeException e) {
                e.printStackTrace();
                model.addAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
                return "members/modify"; // 업로드 실패 시의 뷰로 이동
            }
        } else {
            model.addAttribute("message", "유효한 파일을 선택해주세요.");
            return "members/modify"; // 파일이 선택되지 않은 경우의 뷰로 이동
        }

        return "index"; // 업로드 성공 후 리다이렉트
    }
    
    
    // 관심사
    @GetMapping("/interest")
    public String getInterests(Model model) {
        List<Integer> categoryIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //  1-10 ITRID 사용
        List<InterestVO> interests = memService.getInterestsByCategoryIds(categoryIds);
        
        model.addAttribute("interests", interests);
        return "members/interest"; // interestsPage는 결과를 보여줄 Thymeleaf 뷰
    }
    
    // 관심사 인서트
    @PostMapping("/submitInterest")
    public String submitInterest(
            @RequestParam("category_1") int category1Interest,
            @RequestParam("category_2") int category2Interest,
            @RequestParam("category_3") int category3Interest,
            @RequestParam("category_4") int category4Interest,
            @RequestParam("category_5") int category5Interest,
            @RequestParam("category_6") int category6Interest,
            @RequestParam("category_7") int category7Interest,
            @RequestParam("category_8") int category8Interest,
            @RequestParam("category_9") int category9Interest,
            @RequestParam("category_10") int category10Interest,
            HttpSession session) {
    	System.out.println("interest들어옴 ~"+category1Interest);
        // 로그인한 사용자 ID 가져오기
        MemberVO member = (MemberVO) session.getAttribute("members");
        String memId = member.getMemId();

        // 각 선택된 관심사 ID를 리스트로 생성
        List<Integer> selectedInterests = Arrays.asList(
                category1Interest, category2Interest, category3Interest, 
                category4Interest, category5Interest, category6Interest,
                category7Interest, category8Interest, category9Interest, 
                category10Interest
        );

        // 관심사를 저장하는 서비스 메서드 호출
        memService.saveMemberInterests(memId, selectedInterests);

        return "/members/memjuso"; // 리다이렉트할 페이지
    }
    
    // 주소 관련
    @GetMapping("/addr")
    public String addrView() {
    	
    	return "members/memjuso";
    }
    
    
    @PostMapping("/submitAddr")
    public String submitAddr(@ModelAttribute MemberVO member, HttpSession session) {
        // 세션에서 memId 가져오기
    	System.out.println(member);
        MemberVO sessionMember = (MemberVO) session.getAttribute("members");
        String memId = sessionMember.getMemId();
        
        // memberVO에 세션의 memId 설정
        member.setMemId(memId);

        // 주소 정보를 서비스 계층에 저장
        memService.updateMemberAddr(member);

        return "members/modify";
    }
   
    
}
