package com.board.test.mapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.board.test.dto.UserDto;

public interface UserMapper {

	/* 회원가입 */
	public int insertUser(UserDto userDto);

	/* 로그인정보 */
	public UserDto viewMember(UserDto dto);

	/* 로그인체크 */
	public int loginCheck(UserDto dto);

	/* 로그아웃 */
	public void logout(HttpSession session);

	/* 네아로 회원가입 */
	public int insertNaverUser(Map<String, Object> map);

	/* 네아로 회원가입 check */
	public int selectNaverCheck(Map<String, Object> map);

	public List<UserDto> list();

}
