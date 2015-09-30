package com.zxb.demo.dao;


import com.zxb.demo.model.Coins;

/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public interface CoinsDao {
    Coins selectByUserId(Long id);

    Long addRecord(Coins record);

    Long updteUserCoinNum(Coins record);
}