package com.fullstack.board.entity.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	


}
