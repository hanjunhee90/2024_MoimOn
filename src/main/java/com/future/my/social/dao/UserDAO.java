package com.future.my.social.dao;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.social.vo.UsertestVO;

@Mapper
public interface UserDAO{
	
	public UsertestVO getloginInfo(String username);
	
	public void insertUser(UsertestVO user);

}
