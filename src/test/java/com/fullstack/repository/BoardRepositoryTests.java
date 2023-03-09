package com.fullstack.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fullstack.board.entity.Board;
import com.fullstack.board.entity.Member;
import com.fullstack.board.entity.repository.BoardRepository;

@SpringBootTest
public class BoardRepositoryTests {


	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Member member = Member.builder().email("tlawltjr" + i + "@abc.com").build();
			Board board = Board.builder().title("제목" + i).content("이건 글내용" + i).writer(member).build();
			
			boardRepository.save(board);
		});
	}
	
	//JPQL 실행 테스트 하기
	//JPQL로 수행한 데이터가 리턴될때는 기본적으로 Object타입으로 리턴되어집니다
	//이때 실제 데이터(엔티티 객체)는 위 Object 내에 Object[]형태로 구성되어집니다
	//그래서 수행 후 리턴은 Object로 받고, 실제 Data 객체는 Object[]로 받아서
	//루프를 이용 getting합니다
//	@Test
//	public void testJPQL1() {
//		Object result = boardRepository.getBoardWithWriter(40L);
//		Object[] dataArr = (Object[]) result;
//		System.out.println(Arrays.toString(dataArr));
//	}
	
//	@Transactional
//	@Test
//	public void testJPQL2() {
//		List<Object[]> result = boardRepository.getBoardWithReple(40L);
//		for(Object[] arr : result) {
//			System.out.println(Arrays.toString(arr));
//		}
//	}
	
	@Transactional
	@Test
	public void testJPQL3() {
		
		//방명록에서처럼 리스트에서 필요한 목록을 담은 Page를 리턴하도록
		//Pageable 객체를 메서드에 호출시 같이 보냅니다
		//이렇게 하면 정렬, 페이지, 목록 등이 담겨져 Object[]로 리턴됩니다
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row ->{
			Object[] arr = (Object[])row;
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//특정 게시물 번호를 기준으로 글을 가져오는 테스트 구문작성
//	@Transactional
//	@Test
//	public void readJPQL() {
//		Object result = boardRepository.getBoardByBno(99L);
//		Object[] arr = (Object[])result;
//		System.out.println(Arrays.toString(arr));
//	}
	
	
	
	
	
	
	
	
	
	
}
