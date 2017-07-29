package com.hengda.smart.wuda.m.bean;

/**
 * Created by lenovo on 2017/5/9.
 */

public class CapResult {

    /**
     * status : 1
     * data : {"isSuccess":1}
     * msg : 参数有误
     */

    private int status;
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
        /**
         * isSuccess : 1
         */

        private int isSuccess;

        public int getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(int isSuccess) {
            this.isSuccess = isSuccess;
        }
    }
}
