package com.keduit.service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public Long register(BoardVO bVO) {
        log.info("...register..." + bVO);
        boardMapper.insertSelectKey(bVO);
        return bVO.getBno();
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("...get...");
        BoardVO bVO = boardMapper.read(bno);
        return bVO;
    }

    @Override
    public boolean modify(BoardVO bVO) {
        log.info("...modify...");
        int result = boardMapper.update(bVO);
        if (result > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("...remove...");
        int result = boardMapper.delete(bno);
        if (result > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {

        log.info("...getList...");
        List<BoardVO> list = boardMapper.getListWithPaging(cri);
        return list;
    }
}
