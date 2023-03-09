package com.fullstack.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstack.board.entity.Reply;
import com.fullstack.board.entity.repository.ReplyRepository;

@SpringBootTest
public class EagerFetchExam {

	@Autowired
	ReplyRepository replyRepository;
	
	@Transactional
	@Test
	public void eagerTest() {
		
		Optional<Reply> result = replyRepository.findById(40L);
		
		Reply reply = result.get();
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}
}
