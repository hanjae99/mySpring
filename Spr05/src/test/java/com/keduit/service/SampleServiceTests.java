package com.keduit.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {

    @Setter(onMethod_ = @Autowired)
    private SampleService service;

    @Setter(onMethod_ = @Autowired)
    private SampleTxService serviceTx;

    @Test
    public void testClass() {
        log.info(service);
        log.info(service.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception {
        log.info(service.doAdd("567", "500"));
    }

    @Test
    public void testAdd2() throws Exception {
        log.info(service.doAdd("300", "700"));
    }

    // 에러 발생 테스트
    @Test
    public void testException() throws Exception {
        log.info(service.doAdd("123", "abc"));
    }

    @Test
    public void testLong() {
        String str = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus quisquam velit ea aliquam? Alias rerum perferendis iure eum sint suscipit deleniti ipsa unde sit totam, esse a, molestias doloribus mollitia?" +
                "Facere earum totam odit suscipit obcaecati impedit sint ullam hic, modi minus ad saepe illum magnam perspiciatis praesentium quidem quo at quia. Error harum ex aut aspernatur voluptatibus quod laborum!" +
                "Magnam molestias doloribus doloremque fuga mollitia expedita ipsam blanditiis quis!";

        log.info(str.getBytes().length);
        serviceTx.addData(str);
    }
}
