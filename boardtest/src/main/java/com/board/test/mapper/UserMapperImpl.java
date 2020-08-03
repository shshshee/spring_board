package com.board.test.mapper;

import java.util.List;
import java.util.Map;

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

	/* 네이버 회원가입 */
	@Override
	public int insertNaverUser(Map<String, Object> map) {
		return sqlSession.insert("com.board.test.mapper.UserMapper.insertNaverUser");
	}

	/* 네아로 회원가입 check */
	@Override
	public int selectNaverCheck(Map<String, Object> map) {
		return sqlSession.selectOne("com.board.test.mapper.UserMapper.selectNaverCheck", map);
	}

	@Override
	public List<UserDto> list() {
		return sqlSession.selectList("com.board.test.mapper.UserMapper.list");
	}
}
