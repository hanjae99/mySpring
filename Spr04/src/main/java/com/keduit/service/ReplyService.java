package com.keduit.service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {

    public Long register(ReplyVO rVO);

    public ReplyVO get(Long rno);

    public boolean modify(ReplyVO rVO);

    public boolean remove(Long rno);

    public List<ReplyVO> getList(@Param("cri") Criteria cri, @Param("bno") Long bno);
}
