package com.keduit.persistence;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection(){
        try(
                Connection conn = dataSource.getConnection();
                ){
            log.info(conn);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testMyBatis(){
        try (SqlSession session = sqlSessionFactory.openSession();
                Connection conn = session.getConnection();){
            log.info(session);
            log.info(conn);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
