package com.hengda.smart.wuda.m.bean;

/**
 * Created by lenovo on 2017/4/24.
 */

public class FloorBean {

    /**
     * status : 1
     * data : {"is_valid":0,"museum_id":"0002"}
     * msg : 操作成功
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
         * is_valid : 0
         * museum_id : 0002
         */

        private int is_valid;
        private String museum_id;
        private String museum_name;

        public String getMuseum_name() {
            return museum_name;
        }

        public void setMuseum_name(String museum_name) {
            this.museum_name = museum_name;
        }

        public int getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(int is_valid) {
            this.is_valid = is_valid;
        }

        public String getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(String museum_id) {
            this.museum_id = museum_id;
        }
    }
}
