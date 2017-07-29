package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/18.
 */

/**
 * 创建人：lenovo
 * 创建时间：2017/7/18 14:55
 * 类描述：
 */
public class ResponseBean {

    /**
     * status : 1
     * data : {"success":1}
     * msg : 操作成功
     */

    private int status;
    /**
     * success : 1
     */

    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private int success;

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }
    }
}
