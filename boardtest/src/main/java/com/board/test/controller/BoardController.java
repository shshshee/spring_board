
package com.board.test.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.test.dto.BoardDto;
import com.board.test.dto.Criteria;
import com.board.test.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;

	// 게시물 전체목록 출력하기
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String list(Model model, Criteria cri) {
		// list라는 값으로 전송
		model.addAttribute("list", service.getList());
		return "board/list";

	}

	// 글쓰기창으로 이동
	@RequestMapping(value = "/register", produces = "text/plain;charset=UTF-8")
	public String register(BoardDto board, RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", board.getBno());
		return "board/register";
	}

	// 글저장
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String saveBoard(@ModelAttribute("BoardDto") BoardDto board, RedirectAttributes rttr) {
		System.out.println("register:" + board);
		rttr.addFlashAttribute("result", "success");
		service.InsertBoard(board);
		return "redirect:/board/list";
	}

	// 보기/수정하기 실행
	@GetMapping({ "/get", "/update" })
	public void get(@RequestParam("bno") int bno, Model model) {
		System.out.println("/get and update hit");
		service.hitupdate(bno);
		model.addAttribute("board", service.GetBoardContent(bno));

	}

	// 게시물 수정후 이동
	@PostMapping("/update")
	public String update(BoardDto board, RedirectAttributes rttr) {
		System.out.println("**************update=" + board);
		if (service.update(board)) { // 데이터가 업데이트되면 1을 리턴한다
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}

	// 삭제
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		service.deleteBoard(bno);
		return "redirect:/board/list";
	}

}