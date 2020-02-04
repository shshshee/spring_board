package com.board.test.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.test.dto.BoardDto;
import com.board.test.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService service;

	// 게시물 전체목록 출력하기
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String list(Model model) {
		// list라는 값으로 전송
		model.addAttribute("list", service.getList());
		return "board/list";

	}

	// 글쓰기
	@RequestMapping(value = "/register", produces = "text/plain;charset=UTF-8")
	public String register() {
		return "board/register";
	}

	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String saveBoard(@ModelAttribute("BoardDto") BoardDto board, RedirectAttributes rttr) {
		System.out.println("register:" + board);
		service.InsertBoard(board);
		return "redirect:/board/list";

	}

	// 보기
	@GetMapping("/get")
	public void get(@RequestParam("bno") int bno, Model model) {
		System.out.println("/get");
		model.addAttribute("board", service.GetBoardContent(bno));
	}

	// 삭제
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		service.deleteBoard(bno);
		return "redirect:/board/list";
	}
}
