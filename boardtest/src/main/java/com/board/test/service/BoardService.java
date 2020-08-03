package com.board.test.service;

import java.util.List;

import com.board.test.dto.BoardDto;

public interface BoardService {
	// 게시글리스트조회
	public List<BoardDto> getList();

	public void InsertBoard(BoardDto board);

	public BoardDto GetBoardContent(int bno);

	public boolean deleteBoard(int bno);

	public boolean update(BoardDto board); // 수정

	public boolean hitupdate(int bno);

}
