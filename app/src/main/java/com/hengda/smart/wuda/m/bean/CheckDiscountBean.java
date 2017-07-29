package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/19.
 */

/**
 * 创建人：lenovo
 * 创建时间：2017/7/19 13:56
 * 类描述：
 */
public class CheckDiscountBean {

    /**
     * status : 1
     * data : {"is_youhuiquan_exists":0,"youhuiquan_class_id":0,"price":0,"title":"","next_id":0}
     * msg : 操作成功
     */

    private int status;
    /**
     * is_youhuiquan_exists : 0
     * youhuiquan_class_id : 0
     * price : 0
     * title :
     * next_id : 0
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
        private int is_youhuiquan_exists;
        private int youhuiquan_class_id;
        private int price;
        private String title;
        private int next_id;

        public int getIs_youhuiquan_exists() {
            return is_youhuiquan_exists;
        }

        public void setIs_youhuiquan_exists(int is_youhuiquan_exists) {
            this.is_youhuiquan_exists = is_youhuiquan_exists;
        }

        public int getYouhuiquan_class_id() {
            return youhuiquan_class_id;
        }

        public void setYouhuiquan_class_id(int youhuiquan_class_id) {
            this.youhuiquan_class_id = youhuiquan_class_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNext_id() {
            return next_id;
        }

        public void setNext_id(int next_id) {
            this.next_id = next_id;
        }
    }
}
