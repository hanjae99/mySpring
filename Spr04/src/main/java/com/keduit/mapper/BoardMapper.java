package com.keduit.mapper;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {

//    @Select("select * from board where bno > 0")
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO bVO);

    public void insertSelectKey(BoardVO bVO);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO bVO);

    public int getTotalCount(Criteria cri);

    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
