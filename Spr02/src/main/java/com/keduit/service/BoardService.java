package com.keduit.service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

import java.util.List;

public interface BoardService {

    public Long register(BoardVO bVO);

    public BoardVO get(Long bno);

    public boolean modify(BoardVO bVO);

    public boolean remove(Long bno);

    public List<BoardVO> getList(Criteria cri);

    public int getTotalCount(Criteria cri);
}
