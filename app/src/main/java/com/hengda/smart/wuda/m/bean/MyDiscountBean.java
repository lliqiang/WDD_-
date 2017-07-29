package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/20.
 */

import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/20 14:12
 * 类描述：
 */
public class MyDiscountBean {

    /**
     * status : 1
     * data : [{"youhuiquan_id":"2","price":"20","time":"1970-01-01 08:00:00","title":"111"}]
     * msg : 操作成功
     */

    private int status;
    private String msg;
    /**
     * youhuiquan_id : 2
     * price : 20
     * time : 1970-01-01 08:00:00
     * title : 111
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String youhuiquan_id;
        private String price;
        private String time;
        private String title;

        public String getYouhuiquan_id() {
            return youhuiquan_id;
        }

        public void setYouhuiquan_id(String youhuiquan_id) {
            this.youhuiquan_id = youhuiquan_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
