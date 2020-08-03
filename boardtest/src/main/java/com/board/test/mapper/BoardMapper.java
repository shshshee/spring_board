package com.board.test.mapper;

import java.util.List;

import com.board.test.dto.BoardDto;

public interface BoardMapper {
	public List<BoardDto> getList();

	public BoardDto GetBoardContent(int bno); // 보기

	public int InsertBoard(BoardDto board); // 작성

	public int DeleteBoard(int bno); // 삭제

	public int update(BoardDto board); // 수정

	public int updatehit(int bno); // 조회수 업데이트

	/*
	 * public void insert(BoardDto board);
	 * 
	 * public void insertSelectKey(BoardDto board);
	 */

}
