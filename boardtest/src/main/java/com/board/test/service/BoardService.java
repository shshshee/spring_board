package com.board.test.service;

import java.util.List;

import com.board.test.dto.BoardDto;

public interface BoardService {
	// 게시글리스트조회
	public List<BoardDto> getList();

	public void InsertBoard(BoardDto board);

	public BoardDto GetBoardContent(int bno);

	public void deleteBoard(int bno);

	/*
	 * // 게시물 등록 public void register(BoardDto board);
	 */

}
