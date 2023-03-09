package com.fullstack.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstack.board.entity.Member;
import com.fullstack.board.entity.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTests {

	//회원(Member) 테이블에 데이터 100개 insert합니다
	//memberRepository자동 주입하시고
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Member member = Member.builder().email("tlawltjr" + i + "@abc.com").name("심지석" + i).password("0000").build();
			
			memberRepository.save(member);
		});
	}
}
