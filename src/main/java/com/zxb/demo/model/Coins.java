package com.zxb.demo.model;

import java.io.Serializable;
/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public class Coins implements Serializable {
    private static final long serialVersionUID = 7779055775282460872L;
    private  Long id;
    private Long userId;
    private  Long coinNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Long coinNum) {
        this.coinNum = coinNum;
    }

    @Override
    public String toString() {
        return "Id:"+this.id+", userId:" + this.userId + ",coinNum:"+this.coinNum;
    }
}
