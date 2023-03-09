package com.fullstack.board.service;

import com.fullstack.board.dto.ReplyDTO;
import com.fullstack.board.entity.Reply;
import com.fullstack.board.entity.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.fullstack.board.entity.Board;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getPno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result =  replyRepository
                .getRepliesByBoardOrderByPno(Board.builder().bno(bno).build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply= dtoToEntity(replyDTO);

        replyRepository.save(reply);

    }

    @Override
    public void remove(Long pno) {

        replyRepository.deleteById(pno);
    }


}
