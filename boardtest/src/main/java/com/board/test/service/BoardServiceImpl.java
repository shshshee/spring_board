package com.board.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.test.dto.BoardDto;
import com.board.test.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardMapper mapper;

	// 전체 리스트 가져오기2
	@Override
	public List<BoardDto> getList() {
		return mapper.getList();
	}

	// 작성
	@Override
	public void InsertBoard(BoardDto board) {
		mapper.InsertBoard(board);
	}

	// 조회
	@Override
	public BoardDto GetBoardContent(int bno) {
		return mapper.GetBoardContent(bno);
	}

	// 삭제
	@Override
	public void deleteBoard(int bno) {
		mapper.DeleteBoard(bno);
	}

}
