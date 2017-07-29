package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/22.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/22 10:13
 * 类描述：
 */
public class BuyTicketBean implements Serializable{

    /**
     * status : 1
     * data : [{"ticket_class_id":"1","title":"五大道博物馆","desc":"购票须知等等","desc_up":null,"ori_price":"50","after_discount_price":40,"museum_list":["五大道博物馆","马车游览"]},{"ticket_class_id":"2","title":"哈哈title","desc":"哈哈","desc_up":"哈哈1111","ori_price":"5","after_discount_price":"","museum_list":["马车游览","非物质文化遗产馆"]},{"ticket_class_id":"3","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"4","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"5","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"6","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"7","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"8","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"9","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"10","title":"王光英博物馆","desc":"哈哈哈","desc_up":"购票须知","ori_price":"100","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"11","title":"111","desc":"21","desc_up":"2121","ori_price":"312","after_discount_price":"","museum_list":["王光英复原展厅"]},{"ticket_class_id":"12","title":"拜石博物馆","desc":"拜石博物馆门票","desc_up":"购票须知-拜石博物馆","ori_price":"11","after_discount_price":"","museum_list":["拜石博物馆"]}]
     * msg : 操作成功
     */

    private int status;
    private String msg;
    /**
     * ticket_class_id : 1
     * title : 五大道博物馆
     * desc : 购票须知等等
     * desc_up : null
     * ori_price : 50
     * after_discount_price : 40
     * museum_list : ["五大道博物馆","马车游览"]
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

    public static class DataBean implements Serializable{
        private String ticket_class_id;
        private String title;
        private String desc;
        private Object desc_up;
        private String ori_price;
        private String after_discount_price;
        private List<String> museum_list;

        public String getTicket_class_id() {
            return ticket_class_id;
        }

        public void setTicket_class_id(String ticket_class_id) {
            this.ticket_class_id = ticket_class_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Object getDesc_up() {
            return desc_up;
        }

        public void setDesc_up(Object desc_up) {
            this.desc_up = desc_up;
        }

        public String getOri_price() {
            return ori_price;
        }

        public void setOri_price(String ori_price) {
            this.ori_price = ori_price;
        }

        public String getAfter_discount_price() {
            return after_discount_price;
        }

        public void setAfter_discount_price(String after_discount_price) {
            this.after_discount_price = after_discount_price;
        }

        public List<String> getMuseum_list() {
            return museum_list;
        }

        public void setMuseum_list(List<String> museum_list) {
            this.museum_list = museum_list;
        }
    }
}
