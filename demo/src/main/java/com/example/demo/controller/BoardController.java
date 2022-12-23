package com.example.demo.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;

@Controller
public class BoardController {

	private static final int page_size = 10;
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "list.html";
	}
	
	@GetMapping("/detail/{no}")
	public String detail(@PathVariable("no")Long id, Model model)
	{
		BoardDto boardDto = boardService.getPost(id);
		model.addAttribute("boardDto", boardDto);
		return "detail.html";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write.html";
	}
	
	@PostMapping("/write")
	public String write(BoardDto dto) {
		boardService.savePost(dto); 
		return "redirect:/";
	}
	
	@GetMapping("/edit/{no}")
	public String edit(@PathVariable("no") Long id, Model model) 
	{
		BoardDto boardDto = boardService.getPost(id);
		model.addAttribute("boardDto", boardDto);
		return "edit.html";
	}
	
	@PostMapping("/edit")
	public String edit(BoardDto dto) {
		
		boardService.savePost(dto);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete(Long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
}
