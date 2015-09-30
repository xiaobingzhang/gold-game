package com.zxb.demo.service.impl;

import com.zxb.demo.dao.CoinsDao;
import com.zxb.demo.model.Coins;
import com.zxb.demo.service.CoinsService;
import com.zxb.demo.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */


@Service("coinsService")
public class CoinsServiceImpl implements CoinsService {
    private static Logger logger = Logger.getLogger(CoinsServiceImpl.class);
    @Resource
    private CoinsDao coinsDao;

    /**
     * 添加用户和金币
     *
     * @param coins
     * @return
     */

    @Override
    public Long addUserRecord(Coins coins) {
        Coins coinsAlreadyIn = coinsDao.selectByUserId(coins.getUserId());
        if (coinsAlreadyIn != null) {
            return 0L;
        }
        Long id = coinsDao.addRecord(coins);
        return id;
    }

    /**
     * 给定用户ID, 返回当前金币金额,
     *
     * @param userId
     * @return
     */
    @Override
    public Coins getCoinsByUserId(Long userId) {
        Coins coins = coinsDao.selectByUserId(userId);
        return coins;
    }

    /**
     * 给定user_id1, user_id2, 金额500, 完成从user1 向user2 的账户转账500 金币
     *
     * @param fromUserId
     * @param toUserId
     * @param coinsNum
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Long coinTransfer(Long fromUserId, Long toUserId, Long coinsNum) throws Exception {
        // chech both user;
        Coins fromCoins = coinsDao.selectByUserId(fromUserId);
        Coins toCoins = coinsDao.selectByUserId(toUserId);
        //各种非正常情况考虑
        if (null == fromCoins) {
            return Constant.FROM_USER_INVALID;
        }
        if (null == toCoins) {
            return Constant.TO_USER_INVALID;
        }

        if (fromCoins.getCoinNum().compareTo(coinsNum) < 0) {
            return Constant.FROM_USER_COINS_NOT_ENOUGH;
        }
        //可成功转账的情况，如果下面发生异常则事务回滚，异常抛给controler，由其进行处理
        fromCoins.setCoinNum(fromCoins.getCoinNum() - coinsNum);
        toCoins.setCoinNum(toCoins.getCoinNum() + coinsNum);
        coinsDao.updteUserCoinNum(fromCoins);
        //int a = 3 / 0;//to test transaction
        coinsDao.updteUserCoinNum(toCoins);

        return Constant.TRANSFER_SUCCESS;
    }
}
