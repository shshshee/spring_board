package com.board.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.test.dto.UserDto;
import com.board.test.service.UserService;

@Controller
public class UserController {

	@Inject
	private UserService service;

	// 로그인 폼 띄우기
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	// 로그인처리
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute UserDto dto, HttpSession session) {
		int result = service.loginCheck(dto, session);
		ModelAndView mav = new ModelAndView();
		if (result == 1) { // 로그인 성공
			// 메인화면으로 이동

			UserDto Dto = service.viewMember(dto);
			session.setAttribute("name", Dto.getName());

			mav.setViewName("redirect:board/list");
			mav.addObject("msg", "success");
		} else {
			mav.setViewName("redirect:login");
			mav.addObject("msg", "failure");

		}
		return mav;
	}

	// 로그아웃 처리
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		service.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:board/list");
		return mav;
	}

	// 회원가입 폼으로 돌아감
	@RequestMapping(value = "/signupForm", method = RequestMethod.GET)
	public String signupForm() {
		return "/signupForm";
	}

	// 회원가입
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(@ModelAttribute("UserDto") UserDto userdto, RedirectAttributes rttr) {
		System.out.println("register:" + userdto);
		service.insertUser(userdto);
		return "redirect:/board/list";
	}

}
