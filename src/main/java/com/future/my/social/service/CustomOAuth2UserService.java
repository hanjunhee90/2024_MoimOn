package com.future.my.social.service;

import org.springframework.stereotype.Service;


//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
@Service
public class CustomOAuth2UserService{
	
//	@Autowired
//	IMemberDAO memDao;
//	
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
//		// OAuth2 로그인 사용자 정보를 가져옴
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        System.out.println("oAuth2User: " + oAuth2User.getAttributes());
//
//        // 로그인 제공자 정보 확인
//        String provider = userRequest.getClientRegistration().getRegistrationId();
//        System.out.println("provider: " + provider);
//
//        // 각 제공자에 맞는 응답 객체 생성
//        OAuth2Response oAuth2Response;
//        if (provider.equals("google")) {
//            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
//        } else if (provider.equals("naver")) {
//            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
//        } else if (provider.equals("kakao")) {
//            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
//        } else {
//            return null; // 지원하지 않는 제공자일 경우 null 반환
//        }
//
//        // 사용자 정보 추출
//        String providerId = oAuth2Response.getProviderId(); // 소셜 로그인 고유 ID
//        String email = oAuth2Response.getEmail();           // 사용자 이메일
//        System.out.println("providerId : "+providerId);
//        Map<String, Object> params = new HashMap<>();
//        params.put("email", email);
//        params.put("providerId", providerId);
//
//        // 데이터베이스에서 기존 회원 조회
//        MemberVO user = memDao.findByEmailOrProviderId(params);
//
//        // 기존 회원이 없는 경우 새 사용자로 등록
//        if (user == null) {
//            user = new MemberVO();
//            user.setMemId(providerId); // 소셜 제공자의 고유 ID를 회원 ID로 사용
//            user.setMemName(oAuth2Response.getName());
//            user.setMemSocial(providerId);  // 소셜 로그인 고유 ID 저장
//            user.setMemEmail(email);
//            user.setMemType(provider);      // 소셜 제공자 정보
//            memDao.insertUser(user);       // 회원 테이블에 새로운 사용자 정보 삽입
//
//            // 소셜 로그인 정보 추가
//            SocialLoginVO socialLogin = new SocialLoginVO();
//            socialLogin.setMemId(user.getMemId());
//            socialLogin.setProvider(provider);
//            socialLogin.setProviderUserId(providerId);
//            socialLogin.setEmail(email);
//            memDao.insertSocialLogin(socialLogin); // 소셜 로그인 테이블에 소셜 정보 저장
//        }
//
//        // 사용자 역할 설정
//        String role = "ROLE_USER";
//        return new CustomOAuth2User(oAuth2Response, role);
//    }
}