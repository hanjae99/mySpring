package com.keduit.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    // controller 테스트 전 가짜 mvc 환경을 만드는 작업
    // Before 어노테이션을 작성해놓았기 때문에 Test 동작이 매번 수행되기전 mockMvc 가 세팅됨
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap());

    }

    @Test
    public void testListPaging() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                        .param("pageNum", "4")
                        .param("amount", "10"))
                .andReturn()
                .getModelAndView()
                .getModelMap());

    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "mockMvc로 등록함")
                .param("content", "mockMvc 로 등록한 내용")
                .param("writer", "mockUser")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "21"))
                .andReturn()
                .getModelAndView()
                .getModelMap());
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("title", "modify 로 수정한 제목")
                .param("content", "modify 로 수정한 내용")
                .param("writer", "modifyUser")
                .param("bno", "21")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.get("/board/remove")
                .param("bno", "21")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }
}
