package com.board.test.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.board.test.dto.UserDto;
import com.board.test.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserMapper mapper;

	/* 회원 로그인 체크 */
	@Override
	public int loginCheck(UserDto dto, HttpSession session) {
		int result = mapper.loginCheck(dto);
		if (result == 2) { // true일 경우에만 세션등록 (아이디 비밀번호가 일치할경우)
			UserDto Dto = viewMember(dto);
			// 세션 변수 등록
			session.setAttribute("userid", Dto.getUserid());
			/* session.setAttribute("name", Dto.getName()); */
		}
		return result;
	}

	/* 회원 로그인 정보 */
	@Override
	public UserDto viewMember(UserDto dto) {
		return mapper.viewMember(dto);
	}

	/* 회원가입 */
	@Override
	public void insertUser(UserDto userdto) {
		mapper.insertUser(userdto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
