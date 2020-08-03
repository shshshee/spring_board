package com.board.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.board.test.dto.UserDto;

public interface UserService {
	// 회원 로그인 체크
	public int loginCheck(UserDto dto, HttpSession session);

	// 로그인 정보
	public UserDto viewMember(UserDto dto);

	// 로그아웃
	public void logout(HttpSession session);

	/* 회원가입 */
	public void insertUser(UserDto userdto);

	/* 네이버 회원가입 */
	public int insertNaverUser(Map<String, Object> map);

	/* 네이버 회원가입 check */
	public int selectNaverCheck(Map<String, Object> map);

	public List<UserDto> list();

}
