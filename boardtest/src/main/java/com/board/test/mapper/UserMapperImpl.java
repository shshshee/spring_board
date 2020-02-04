package com.board.test.mapper;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.test.dto.UserDto;

@Repository
public class UserMapperImpl implements UserMapper {

	@Inject
	private SqlSession sqlSession;

	/* 회원 로그인 체크 */
	@Override
	public int loginCheck(UserDto dto) {
		int count = sqlSession.selectOne("com.board.test.mapper.UserMapper.loginCheck", dto);
		if (count == 0) // 검색된 결과가 없을 때
			return 1;
		else // select 검색된 결과가 있을때 1
			return 2;
	}

	/* 로그인 정보 */
	@Override
	public UserDto viewMember(UserDto dto) {
		return sqlSession.selectOne("com.board.test.mapper.UserMapper.viewMember", dto);
	}

	/* 로그아웃 */
	@Override
	public void logout(HttpSession session) {

	}

	/* 회원가입 */
	@Override
	public int insertUser(UserDto userdto) {
		return sqlSession.insert("com.board.test.mapper.UserMapper.insertUser");
	}

	/*
	 * @Override public UserDto get(UserDto userDto) { return
	 * sqlSession.selectOne("com.board.test.mapper.UserMapper.loginCheck", userDto);
	 * }
	 */
}
