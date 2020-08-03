package com.board.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.test.dto.testDTO;

@RestController
public class SampleController {

	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		return "안녕하세요";
	}

	@GetMapping(value = "/getSample")
	public Map<String, testDTO> getSample() {
		Map<String, testDTO> map = new HashMap<>();
		map.put("first", new testDTO(111, "아아아", "오오오"));
		return map;
	}

	@PostMapping("/get")
	public testDTO get(@RequestBody testDTO test) {
		return test;
	}

}
