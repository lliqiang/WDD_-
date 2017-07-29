package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/19.
 */

/**
 * 创建人：lenovo
 * 创建时间：2017/7/19 13:17
 * 类描述：
 */
public class UserBean {

    /**
     * status : 1
     * data : 已有133人使用五大道游客服务中心APP
     * msg : 操作成功
     */

    private int status;
    private String data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
