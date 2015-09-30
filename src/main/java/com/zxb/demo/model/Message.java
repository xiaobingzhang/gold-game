package com.zxb.demo.model;

import java.io.Serializable;
/**
 * @author zxb
 * @version 1.0.0
 * @since 2015-09-29
 */

public class Message<T> implements Serializable {
    private  boolean sucess;
    private T object;
    private String errorMsg;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
