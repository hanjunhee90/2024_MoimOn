package com.future.my.ouath.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig11 {

//    @Autowired
//    private CustomOAuth2UserService customOAuth2UserService;
//
//    // PasswordEncoder Bean 설정
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // AuthenticationManager Bean 정의
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	
//    	 // 세션 관리 설정: 항상 세션 생성
//        http.sessionManagement(session -> 
//            session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//        );
//        // CSRF 비활성화
//        http.csrf(csrf -> csrf.disable());
//
//        // HTTP 기본 인증 비활성화
//        http.httpBasic(basic -> basic.disable());
//        
//        http.formLogin(formLogin -> formLogin.disable()); // 기본 로그인 폼 비활성화
//
//        // 로그아웃 설정
//        http.logout(logout -> logout
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//        );
//
//        // OAuth2 로그인 설정
//        http.oauth2Login(oauth2 -> oauth2
//                .userInfoEndpoint(userInfoEndpoint -> 
//                    userInfoEndpoint.userService(customOAuth2UserService))
//        );
//
//        // 권한 설정
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/index.html", "/oauth2/**", "/register/**", "/public/**", "/api/**", "/fonts/**", "/css/**", "/js/**", "/images/**", "/register/login").permitAll()
//                .anyRequest().authenticated()
//        );
//
//        return http.build();
//    }
}
