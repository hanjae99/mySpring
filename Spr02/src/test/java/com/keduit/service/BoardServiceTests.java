package com.keduit.service;

import com.keduit.domain.BoardVO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    public void testExist(){
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister(){
        BoardVO bVO = new BoardVO();
        bVO.setTitle("service 제목");
        bVO.setContent("service insert 내용");
        bVO.setWriter("기웅");

        long bno = service.register(bVO);
        log.info("생성된 게시글 번호: " + bno);
    }

    @Test
    public void testGet(){
        BoardVO bVO = service.get(3L);
        log.info(bVO);
    }

    @Test
    public void testGetList(){
        service.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testModify(){
        BoardVO bVO = new BoardVO();
        bVO.setBno(4L);
        bVO.setTitle("상모햄이 수정한 제목");
        bVO.setContent("상모햄이 수정한 내용");
        bVO.setWriter("쿠상모");

        if (service.modify(bVO)){
            log.info("업데이트 성공!");
        }else {
            log.info("업데이트 실패");
        }
    }

    @Test
    public void testRemove(){
        if (service.remove(4L)){
            log.info("삭제 성공!");
        }else {
            log.info("삭제 실패");
        }
    }
}
