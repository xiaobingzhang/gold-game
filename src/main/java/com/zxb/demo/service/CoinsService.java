package com.zxb.demo.service;

import com.zxb.demo.model.Coins;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public interface CoinsService {
    public Long addUserRecord(Coins coins);

    public Coins getCoinsByUserId(Long userId);

    public Long coinTransfer(Long fromUserId, Long toUserId, Long coinsNum) throws Exception;
}
