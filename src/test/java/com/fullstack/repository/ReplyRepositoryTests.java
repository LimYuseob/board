package com.fullstack.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstack.board.entity.Board;
import com.fullstack.board.entity.Reply;
import com.fullstack.board.entity.repository.ReplyRepository;

@SpringBootTest
public class ReplyRepositoryTests {


	@Autowired
	private ReplyRepository replyRepository;
	
//	@Test
//	public void repleTests() {
//		IntStream.rangeClosed(1, 100).forEach(i->{
//			//임의의 board.bno를 발생시킵니다 단 현재 board테이블에는
//			//100개의 글만 작성했으니, 범위는 1~100까지만 해야합니다
//			long bno = (long)(Math.random() * 100) + 1;
//			
//			//Board 테이블(객체)에 reple 테이블은 관계(FK)로 맺어진 상태이기에
//			//Board 객체를 줘야하고, 이렇게 되면, FK 값을 기준으로 댓글들이 생성됩니다
//			//따라서 위에서 발생한 bno값을 Board객체의 bno값으로 설정합니다
//			Board board = Board.builder().bno(bno).build();
//			
//			//bno값을 가진(Board 테이블내에 존재하는 ) Board 객체를 
//			//reple 객체의 관계롤 넣어주면, 공통키를 기준으로 Board에 존재하는
//			//bno인 경우에만 Insert가 됩니다
//			
//			Reple reple = Reple.builder().text("이건" + board.getBno() + "제목글의 " + i + "번째 댓글 입니다").board(board).repler("비회원").build();
//			
//			repleRepository.save(reple);
//		});
//	}
	
	//메서드 바디를 이용한 테스트
	@Test
	public void testReplyByBoard() {
//		List<Reple> result = repleRepository.findByBoard_Bno(31L);
//		System.out.println(result.get(0));
		List<Reply> repList = replyRepository.getRepliesByBoardOrderByPno(Board.builder().bno(11L).build());
		
		repList.forEach(t->System.out.println(t));
	}
	
	//board 객체의 글번호를 기준으로 댓글 목록 조회하기 테스트
	
	
	
}
