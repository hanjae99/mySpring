package com.keduit.mapper;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {

    public int insertSelectKey(ReplyVO vo);

    public ReplyVO read(Long rno);

    public boolean modify(ReplyVO vo);

    public boolean remove(Long rno);

    public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
}
