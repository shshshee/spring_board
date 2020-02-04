package com.board.test.mapper;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.test.dto.BoardDto;

@Repository
public class BoardMapperImpl implements BoardMapper {
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BoardDto> getList() {
		return sqlSession.selectList("com.board.test.mapper.BoardMapper.getList");
	}

	@Override
	public int InsertBoard(BoardDto board) {

		return sqlSession.insert("com.board.test.mapper.BoardMapper.InsertBoard", board);

	}

	@Override
	public BoardDto GetBoardContent(int bno) {
		return sqlSession.selectOne("com.board.test.mapper.BoardMapper.GetBoardContent", bno);
	}

	@Override
	public int DeleteBoard(int bno) {
		return sqlSession.insert("com.board.test.mapper.BoardMapper.DeleteBoard", bno);
	}

}
