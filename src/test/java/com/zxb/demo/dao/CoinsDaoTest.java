package com.zxb.demo.dao;


import com.zxb.demo.BaseTest;
import com.zxb.demo.model.Coins;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public class CoinsDaoTest extends BaseTest {
    private static Logger logger = Logger.getLogger(CoinsDaoTest.class);
    @Resource
    private CoinsDao coinsDao;

    @Test
    public void testAddAndFindAndUpdate() {
        Coins coins = new Coins();
        coins.setUserId(10000000L);
        coins.setCoinNum(1000L);
        Long ret = coinsDao.addRecord(coins);
        Long cmp = 1L;
        assertEquals(ret, cmp);
        coins = coinsDao.selectByUserId(10000001L);
        assertNull(coins);
        coins = coinsDao.selectByUserId(10000000L);
        assertNotNull(coins);
        coins.setCoinNum(1L);
        ret = coinsDao.updteUserCoinNum(coins);
        cmp = 1L;
        assertEquals(ret, cmp);

    }


}