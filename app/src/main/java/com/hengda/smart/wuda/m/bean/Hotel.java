package com.hengda.smart.wuda.m.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/10.
 */

public class Hotel {

    /**
     * status : 1
     * data : [{"hotel_id":"0003","price":"31231","list_img":"http://192.168.10.158/wdd/resource/hotel/0003/images/0003_list.png","phone":"312312","title":null,"address":null}]
     * msg : 操作成功
     */

    private int status;
    private String msg;
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
        /**
         * hotel_id : 0003
         * price : 31231
         * list_img : http://192.168.10.158/wdd/resource/hotel/0003/images/0003_list.png
         * phone : 312312
         * title : null
         * address : null
         */

        private String hotel_id;
        private String price;
        private String list_img;
        private String phone;
        private String title;
        private String address;

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getList_img() {
            return list_img;
        }

        public void setList_img(String list_img) {
            this.list_img = list_img;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
