package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/27.
 */

import java.io.Serializable;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/27 17:08
 * 类描述：
 */
public class MsgBean implements Serializable {
    private int code;

    public MsgBean(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
