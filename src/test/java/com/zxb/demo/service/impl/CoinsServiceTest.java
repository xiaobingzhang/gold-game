package com.zxb.demo.service.impl;

import com.zxb.demo.BaseTest;
import com.zxb.demo.dao.CoinsDao;
import com.zxb.demo.model.Coins;
import com.zxb.demo.service.CoinsService;
import com.zxb.demo.util.Constant;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public class CoinsServiceTest extends BaseTest {
    private static Logger logger = Logger.getLogger(CoinsServiceTest.class);

    @Resource
    private CoinsDao coinsDao;

    @Resource
    private CoinsService coinsService;

    @Test
    public void addUserRecordTest() {
        Coins coins = new Coins();
        coins.setUserId(3123124L);
        coins.setCoinNum(9L);

        Long id = coinsService.addUserRecord(coins);
        Long ret = 1L;
        logger.info(id);
        assertEquals(id, ret);

        id = coinsService.addUserRecord(coins);
        ret = 0L;
        logger.info(id);
        assertEquals(id, ret);
    }

    @Test
    public void getCoinsByUserId() {
        Coins ret = coinsService.getCoinsByUserId(3123124L);
        assertNull(ret);

        Coins coins = new Coins();
        coins.setUserId(3123124L);
        coins.setCoinNum(9L);

        coinsService.addUserRecord(coins);
        ret = coinsService.getCoinsByUserId(3123124L);
        assertNotNull(ret);
    }

    @Test
    public void coinTransfer() throws Exception {
        Long fromUserId = 1234567890L;
        Long toUserId = 1234567891L;
        Long coinsNum = 200L;

        Coins coins = new Coins();
        coins.setUserId(fromUserId);
        coins.setCoinNum(100L);

        coinsService.addUserRecord(coins);
        Long ret = coinsService.coinTransfer(fromUserId, toUserId, coinsNum);
        assertEquals(ret, Constant.TO_USER_INVALID);

        ret = coinsService.coinTransfer(toUserId, fromUserId, coinsNum);
        assertEquals(ret, Constant.FROM_USER_INVALID);

        coins = new Coins();
        coins.setUserId(toUserId);
        coins.setCoinNum(200L);
        coinsService.addUserRecord(coins);

        ret = coinsService.coinTransfer(fromUserId, toUserId, 200L);
        assertEquals(ret, Constant.FROM_USER_COINS_NOT_ENOUGH);

        ret = coinsService.coinTransfer(toUserId, fromUserId, 200L);
        assertEquals(ret, Constant.TRANSFER_SUCCESS);
    }
}
