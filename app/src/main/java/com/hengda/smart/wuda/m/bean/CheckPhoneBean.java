package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/20.
 */

import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/20 8:45
 * 类描述：
 */
public class CheckPhoneBean {

    /**
     * status : 1
     * data : []
     * msg : 操作成功
     */

    private int status;
    private String msg;
    private List<?> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
