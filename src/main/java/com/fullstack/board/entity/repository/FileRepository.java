package com.fullstack.board.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.board.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long>{

}