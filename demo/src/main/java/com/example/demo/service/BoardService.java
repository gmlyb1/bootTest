package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Board;
import com.example.demo.domain.repository.BoardRepository;
import com.example.demo.dto.BoardDto;

@Service
public class BoardService {

	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public void savePost(BoardDto boardDto) {
		boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional
	public List<BoardDto> getBoardList() {
		List<Board> boards = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boards) {
			BoardDto dto = BoardDto.builder()
					.id(board.getId())
					.title(board.getTitle())
					.content(board.getContent())
					.writer(board.getWriter())
					.createdDate(board.getCreatedDate())
					.build();
			
			boardDtoList.add(dto);
		}
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(Long id) {
		Optional<Board> boardWrapper = boardRepository.findById(id);
		if(boardWrapper.isPresent())
		{
			Board board = boardWrapper.get();
			
			BoardDto boardDto = BoardDto.builder()
					.id(board.getId())
					.title(board.getTitle())
					.content(board.getContent())
					.writer(board.getWriter())
					.createdDate(board.getCreatedDate())
					.build();
			
			return boardDto;
		}
		return null;
	}
	
	@Transactional
	public void deletePost(Long id) {
		Optional<Board> optBoard = boardRepository.findById(id);
		if(optBoard.isPresent()) {
			Board board = optBoard.get();
			boardRepository.deleteById(id);
		}
	}
}
