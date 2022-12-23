package com.example.demo.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	
}
