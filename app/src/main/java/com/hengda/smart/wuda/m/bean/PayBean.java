package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/24.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/24 13:39
 * 类描述：
 */
public class PayBean implements Serializable {


    /**
     * status : 1
     * data : {"discount":1,"discount_class_id":0,"youhuiquan_info":[{"youhui_id":"2","youhui_price":"20","title":null,"desc":null,"expire_time":"","item_is_valid":1}],"sum_price":62,"youhuiquan_has_valid":1}
     * msg : 操作成功
     */

    private int status;
    /**
     * discount : 1
     * discount_class_id : 0
     * youhuiquan_info : [{"youhui_id":"2","youhui_price":"20","title":null,"desc":null,"expire_time":"","item_is_valid":1}]
     * sum_price : 62
     * youhuiquan_has_valid : 1
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

    public static class DataBean implements Serializable{
        private int discount;
        private int discount_class_id;
        private double sum_price;
        private int youhuiquan_has_valid;
        /**
         * youhui_id : 2
         * youhui_price : 20
         * title : null
         * desc : null
         * expire_time :
         * item_is_valid : 1
         */

        private List<YouhuiquanInfoBean> youhuiquan_info;

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getDiscount_class_id() {
            return discount_class_id;
        }

        public void setDiscount_class_id(int discount_class_id) {
            this.discount_class_id = discount_class_id;
        }

        public double getSum_price() {
            return sum_price;
        }

        public void setSum_price(double sum_price) {
            this.sum_price = sum_price;
        }

        public int getYouhuiquan_has_valid() {
            return youhuiquan_has_valid;
        }

        public void setYouhuiquan_has_valid(int youhuiquan_has_valid) {
            this.youhuiquan_has_valid = youhuiquan_has_valid;
        }

        public List<YouhuiquanInfoBean> getYouhuiquan_info() {
            return youhuiquan_info;
        }

        public void setYouhuiquan_info(List<YouhuiquanInfoBean> youhuiquan_info) {
            this.youhuiquan_info = youhuiquan_info;
        }

        public static class YouhuiquanInfoBean implements Serializable{
            private String youhui_id;
            private String youhui_price;
            private Object title;
            private Object desc;
            private String expire_time;
            private int item_is_valid;

            public String getYouhui_id() {
                return youhui_id;
            }

            public void setYouhui_id(String youhui_id) {
                this.youhui_id = youhui_id;
            }

            public String getYouhui_price() {
                return youhui_price;
            }

            public void setYouhui_price(String youhui_price) {
                this.youhui_price = youhui_price;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getDesc() {
                return desc;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public String getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(String expire_time) {
                this.expire_time = expire_time;
            }

            public int getItem_is_valid() {
                return item_is_valid;
            }

            public void setItem_is_valid(int item_is_valid) {
                this.item_is_valid = item_is_valid;
            }
        }
    }
}
