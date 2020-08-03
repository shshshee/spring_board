package com.board.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.test.dto.UserDto;
import com.board.test.service.UserService;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class UserController {

	/* naverlogin */
	private naverLogin naverLogin;
	private String apiResult = null;

	@Autowired
	BCryptPasswordEncoder bcryptPE;

	@Inject
	private UserService service;

	@Autowired
	private void setnaverLogin(naverLogin naverLogin) {
		this.naverLogin = naverLogin;
	}

	// 네이버 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverlogin(Model model, HttpSession session) {

		// 네이버 아이디로 인증 url을 생성하기 위해 naverlogin 클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLogin.getAuthorizationUrl(session);
		System.out.print("네이버:" + naverAuthUrl);

		model.addAttribute("url", naverAuthUrl);

		// return page
		return "/login";
	}

	// 네이버 로그인 callback
	@RequestMapping(value = "callback.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		// 아이디 없으면 회원가입 or 있으면 그대로 로그인하기
		System.out.println("callback controller");
		OAuth2AccessToken oauthToken = naverLogin.getAccessToken(session, code, state);

		/* 로그인한 사용자 정보 읽어오기 */
		apiResult = naverLogin.getUserProfile(oauthToken);
		System.out.println("APIinfo=>" + apiResult.toString());

		int result = 0; // 회원가입 성공여부 result

		JSONParser parser = new JSONParser(); // jsonparser 선언

		// 코드 정리 필요
		try {
			// json 파싱
			JSONObject userinfo = (JSONObject) parser.parse(apiResult);
			JSONObject res = (JSONObject) userinfo.get("response");

			String sns_email = res.get("email").toString();
			String sns_name = res.get("name").toString();
			String sns_id = res.get("id").toString();

			Map<String, Object> map = new HashMap<>();
			map.put("name", sns_email);
			map.put("email", sns_name);
			map.put("id", sns_id);

			result = service.insertNaverUser(map);
			System.out.println("parseAPIemail=>" + map.toString());

			model.addAttribute("result", map.get("email"));
			session.setAttribute("name", map.get("email")); // 이메일정보 session에 저장

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/loginSuccess";

	}

	// 로그인 폼 띄우기
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * login() { return "/login"; }
	 */

	@RequestMapping(value = "user/userist")
	public String list(Model model) {
		model.addAttribute("user", service.list());
		return "user/userList";

	}

	// 로그인처리 -> 암호가 같아도 다르게찍힘

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute UserDto dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int result = service.loginCheck(dto, session);

		if (result == 2) { // 로그인 성공시
			UserDto Dto = service.viewMember(dto);
			session.setAttribute("name", Dto.getName());

			mav.setViewName("redirect:board/list");
			mav.addObject("msg", "success");

		} else { // 로그인 실패시
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
	public String insertUser(@RequestParam("userid") String userid, @RequestParam("pwd") String pwd,
			@RequestParam("name") String name, UserDto userdto) {
		userdto.setUserid(userid);
		userdto.setName(name);
		userdto.setPwd(bcryptPE.encode(pwd));
		service.insertUser(userdto);
		return "redirect:/board/list";
	}

}
