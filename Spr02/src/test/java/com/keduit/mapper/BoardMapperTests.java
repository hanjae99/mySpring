package com.keduit.mapper;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper boardMapper;

    @Test
    public void testGetList(){
        boardMapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testPaging(){
        Criteria cri = new Criteria();

        cri.setAmount(10);
        cri.setPageNum(7);

        List<BoardVO> list = boardMapper.getListWithPaging(cri);
        list.forEach(board -> log.info(board));
    }

    @Test
    public void testInsert(){
        BoardVO bVO = new BoardVO();
        bVO.setTitle("insert test 코드 입력 제목");
        bVO.setContent("insert test 코드 입력 내용");
        bVO.setWriter("광현");
        boardMapper.insert(bVO);

        log.info(bVO);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO bVO = new BoardVO();
        bVO.setTitle("insertSelectKey 제목");
        bVO.setContent("insertSelectKey 내용");
        bVO.setWriter("기웅");
        boardMapper.insertSelectKey(bVO);

        log.info(bVO);
    }

    @Test
    public void testRead(){
        BoardVO bVO = boardMapper.read(5L);
        log.info(bVO);
    }

    @Test
    public void testDelete(){
        int result = boardMapper.delete(7L);
        log.info("...delete...");
        log.info("delete 갯수 => " + result);
    }

    @Test
    public void testUpdate(){
        BoardVO bVO = new BoardVO();
        bVO.setBno(2L);
        bVO.setTitle("과자먹고싶다");
        bVO.setContent("카스테라 주세요");
        bVO.setWriter("상현");

        int result = boardMapper.update(bVO);
        if (result > 0){
            log.info("업데이트 성공!");
        }else {
            log.info("업데이트 실패!");
        }
    }

    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setKeyword("궁서");
        cri.setType("TW");

        List<BoardVO> list = boardMapper.getListWithPaging(cri);
        list.forEach(board -> log.info(board));
    }
}

