package com.zxb.demo; /**
* Created by lym on 2015/9/29.
*/

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@TransactionConfiguration
@Transactional
/**
 * 单测加上事务避免污染数据库
 */
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static Logger logger = Logger.getLogger(BaseTest.class);
    @Test
    public void test(){

    }

}
