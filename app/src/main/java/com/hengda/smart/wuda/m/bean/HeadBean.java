package com.hengda.smart.wuda.m.bean;

/**
 * Created by lenovo on 2017/4/27.
 */

public class HeadBean {

    /**
     * status : 1
     * data : {"isSuccess":1,"url":"url"}
     * msg : 操作成功
     */

    private int status;
    /**
     * isSuccess : 1
     * url : url
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
        private int isSuccess;
        private String url;

        public int getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(int isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
