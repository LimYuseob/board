package com.fullstack.board.service;

import com.fullstack.board.dto.ReplyDTO;
import com.fullstack.board.entity.Board;
import com.fullstack.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); //댓글의 등록

    List<ReplyDTO> getList(Long bno); //특정 게시물의 댓글 목록

    void modify(ReplyDTO replyDTO); //댓글 수정

    void remove(Long pno); //댓글 삭제

    //DTO --> Entity변환
    default Reply dtoToEntity(ReplyDTO replyDTO){

    	//제목글 얻어와야 하기에 Board 객체 생성
        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .pno(replyDTO.getPno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    //reple객체를 repleDTO로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    default ReplyDTO entityToDTO(Reply reply){

        ReplyDTO dto = ReplyDTO.builder()
                .pno(reply.getPno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;

    }
}