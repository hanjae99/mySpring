package com.keduit.service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.BoardMapper;
import com.keduit.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService{

    private final ReplyMapper mapper;

    @Transactional
    @Override
    public Long register(ReplyVO rVO) {
        log.info("...impl register...");
        long result = mapper.insertSelectKey(rVO);
//        return rVO.getRno();
        return result;
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("...impl get...");
        ReplyVO rVO = mapper.read(rno);
        return rVO;
    }

    @Override
    public boolean modify(ReplyVO rVO) {
        log.info("...impl modify...");
        return mapper.modify(rVO);
    }

    @Override
    public boolean remove(Long rno) {
        log.info("...impl remove...");
        return mapper.remove(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("...impl getListWithPaging");
        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {
        log.info("...getListPage...");
        return new ReplyPageDTO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri, bno)
        );
    }


}
