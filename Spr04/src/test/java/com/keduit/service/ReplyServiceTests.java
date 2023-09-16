package com.keduit.service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
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
public class ReplyServiceTests {

    @Setter(onMethod_ = @Autowired)
    private ReplyService service;

    @Test
    public void testRegister(){
        ReplyVO rVO = new ReplyVO();
        rVO.setBno(35L);
        rVO.setReply("impl test");
        rVO.setReplyer("impl user");

        long result = service.register(rVO);
        log.info(result);
    }

    @Test
    public void testGet(){
        ReplyVO rVO = service.get(22L);
        log.info(rVO);
    }

    @Test
    public void testModify(){
        ReplyVO rVO = new ReplyVO();
        rVO.setReply("서비스에서 수정한 내용");
        rVO.setRno(1L);

        if (service.modify(rVO)){
            log.info("수정 성공!");
        }else {
            log.info("수정 실패");
        }
    }

    @Test
    public void testRemove(){
        if (service.remove(22L)){
            log.info("삭제 성공!");
        }else {
            log.info("삭제 실패");
        }
    }

    @Test
    public void testGetList(){
        List<ReplyVO> list = service.getList(new Criteria(2, 10), 35L);

        list.forEach(rVO -> log.info(rVO));
    }
}
