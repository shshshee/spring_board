package com.board.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.test.dto.UserDto;
import com.board.test.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BCryptPasswordEncoder bcryptPE;

	@Inject
	private UserMapper mapper;

	@Override
	public List<UserDto> list() {
		return mapper.list();
	}

	@Override
	public int insertNaverUser(Map<String, Object> map) {
		int count = selectNaverCheck(map);
		int result = 0;

		if (count < 1) {
			result = mapper.insertNaverUser(map);
		}

		return result;
	}

	@Override
	public int selectNaverCheck(Map<String, Object> map) {

		return mapper.selectNaverCheck(map);
	}

	/* 회원 로그인 체크 */
	@Override
	public int loginCheck(UserDto dto, HttpSession session) {

		int result = mapper.loginCheck(dto); // 아이디 비밀번호 일치한것을 찾았을 경우
		if (result == 2) { // true일 경우에만 세션등록 (아이디 비밀번호가 일치할경우)
			UserDto Dto = viewMember(dto);
			// 세션 변수 등록
			session.setAttribute("userid", Dto.getUserid());
			session.setAttribute("pwd", Dto.getPwd());
			session.setAttribute("name", Dto.getName());

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

	/* 로그아웃 */
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
