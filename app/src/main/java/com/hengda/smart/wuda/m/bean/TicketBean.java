package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/20.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/20 16:46
 * 类描述：
 */
public class TicketBean implements Serializable {


    /**
     * status : 1
     * data : [{"ticket_id":"3","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"4","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"5","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"6","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"7","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"8","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"9","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"10","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"11","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"","ori_price":null,"after_discount_price":"","status":"1","museum_list":["五大道博物馆","五大道博物馆"]},{"ticket_id":"Y56562","title":"五大道博物馆","desc":"购票须知等等","ticket_img":"","tiaoxingma_img":"","create_time":"2017.07.19","expire_time":"2017.07.22","ori_price":null,"after_discount_price":"","status":"4","museum_list":["五大道博物馆","五大道博物馆"]}]
     * msg : 操作成功
     */

    private int status;
    private String msg;
    /**
     * ticket_id : 3
     * title : 五大道博物馆
     * desc : 购票须知等等
     * ticket_img :
     * tiaoxingma_img :
     * create_time : 2017.07.19
     * expire_time :
     * ori_price : null
     * after_discount_price :
     * status : 1
     * museum_list : ["五大道博物馆","五大道博物馆"]
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
        private String ticket_id;
        private String title;
        private String desc;
        private String ticket_img;
        private String tiaoxingma_img;
        private String create_time;
        private String expire_time;
        private Object ori_price;
        private String after_discount_price;
        private String status;
        private List<String> museum_list;

        public String getTicket_id() {
            return ticket_id;
        }

        public void setTicket_id(String ticket_id) {
            this.ticket_id = ticket_id;
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

        public String getTicket_img() {
            return ticket_img;
        }

        public void setTicket_img(String ticket_img) {
            this.ticket_img = ticket_img;
        }

        public String getTiaoxingma_img() {
            return tiaoxingma_img;
        }

        public void setTiaoxingma_img(String tiaoxingma_img) {
            this.tiaoxingma_img = tiaoxingma_img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public Object getOri_price() {
            return ori_price;
        }

        public void setOri_price(Object ori_price) {
            this.ori_price = ori_price;
        }

        public String getAfter_discount_price() {
            return after_discount_price;
        }

        public void setAfter_discount_price(String after_discount_price) {
            this.after_discount_price = after_discount_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getMuseum_list() {
            return museum_list;
        }

        public void setMuseum_list(List<String> museum_list) {
            this.museum_list = museum_list;
        }
    }
}
