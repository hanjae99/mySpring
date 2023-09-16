package com.keduit.mapper;

import com.keduit.domain.BoardVO;
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
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    @Test
    public void testMapper(){
        log.info(mapper);
    }

    @Test
    public void testCreate(){
        log.info("...create...");
        IntStream
                .range(1, 11)
                .forEach(i -> mapper.insertSelectKey(new ReplyVO(null, i + 35L, "이건 답변내용" + i, "한재" + i, null, null)));
    }

    @Test
    public void testRead(){
        log.info("...read...");
        mapper.read(5L);
    }

    @Test
    public void testGetListWithPaging(){
        log.info("...ListWithPaging...");

        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(10);

        List<ReplyVO> replies = mapper.getListWithPaging(cri, 35L);
    }

    @Test
    public void testModify(){
        log.info("...update...");

        ReplyVO vo = new ReplyVO();
        vo.setRno(5L);
        vo.setReply("수정한 답변 내용");

        if (mapper.modify(vo)){
            log.info("업데이트 성공!");
        }else {
            log.info("업데이트 실패");
        }
    }

    @Test
    public void testRemove(){
        log.info("...remove...");

        if (mapper.remove(5L)){
            log.info("삭제 성공!");
        }else {
            log.info("삭제 실패");
        }
    }
}
