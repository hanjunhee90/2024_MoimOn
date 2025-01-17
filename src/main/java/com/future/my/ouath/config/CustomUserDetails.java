package com.future.my.ouath.config;

import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.future.my.member.vo.MemberVO;

//public class CustomUserDetails implements UserDetails, Serializable {
public class CustomUserDetails {

	/*
	 * private static final long serialVersionUID = 1L; // 직렬화 ID 추가
	 * 
	 * private final MemberVO member;
	 * 
	 * public CustomUserDetails(MemberVO member) { this.member = member; }
	 * 
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
	 * 권한 설정, 기본 권한 USER로 설정 return Collections.singletonList(new
	 * SimpleGrantedAuthority("ROLE_USER")); }
	 * 
	 * @Override public String getPassword() { return member.getMemPass(); }
	 * 
	 * @Override public String getUsername() { return member.getMemId(); }
	 * 
	 * @Override public boolean isAccountNonExpired() { return true; }
	 * 
	 * @Override public boolean isAccountNonLocked() { return true; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { return true; }
	 * 
	 * @Override public boolean isEnabled() { return true; }
	 * 
	 * // Getter for the member field public MemberVO getMember() { return
	 * this.member; }
	 */
}
