package com.board.test.dto;

public class pageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public pageDTO(Criteria cri, int total) {
		this.total = total;
		this.cri = cri;
		// 페이징의 끝 번호 계산
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		// 페이징의 시작 번호 계산
		this.startPage = this.endPage - 9;

		// total을 통한 endPage의 재계산
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		// 이전 계산
		this.prev = this.startPage > 1;
		// 다음 계산
		this.next = this.endPage < realEnd;
	}

}
