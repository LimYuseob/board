package com.fullstack.board.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.board.entity.Board;
import com.fullstack.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Modifying//JPQL을 이용해서 Update, Delete 시엔 반드시 선언하셔야 합니다
	@Query("Delete From Reply r Where r.board.bno =:bno")
	void deleteByBno(@Param("bno") Long bno);

	//메서드 쿼리를 이용한 bno에 해당하는 Reple Entity 얻어내기
	//기본 문법은 find or get + (엔티티이름) + By + 변수 이름 형식인데
	//엔티티 이름은 생략가능합니다
	List<Reply> findByBoard_Bno(Long bno);
	
	//정렬을 이용한 특정 글의 댓글 리스트 얻어내기
	List<Reply> getRepliesByBoardOrderByPno(Board board);
	
	
}
