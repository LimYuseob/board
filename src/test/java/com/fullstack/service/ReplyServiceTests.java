package com.fullstack.service;

import java.util.List;

import com.fullstack.board.dto.ReplyDTO;
import com.fullstack.board.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReplyServiceTests {

	@Autowired
	private ReplyService replyService;
	
	@Test
	public void testRepList() {
		Long bno = 95L;
		List<ReplyDTO> repList = replyService.getList(bno);
		repList.forEach(dto->System.out.println(dto));
	}
	
}
