package com.hengda.smart.wuda.m.bean;

/**
 * Created by lenovo on 2017/4/13.
 */

public class ScanBean {

    /**
     * status : 1
     * data : {"tips":"","museum_id":"0001","type":1}
     * msg :
     */

    private int status;
    /**
     * tips :
     * museum_id : 0001
     * type : 1
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
        private String tips;
        private int museum_id;
        private int type;

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public int getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(int museum_id) {
            this.museum_id = museum_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
