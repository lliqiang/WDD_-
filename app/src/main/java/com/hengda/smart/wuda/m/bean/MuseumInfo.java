package com.hengda.smart.wuda.m.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/13.
 */

public class MuseumInfo {


    /**
     * status : 1
     * data : {"museum_id":"0001","imgs":["http://192.168.10.158/wdd_second/resource/museum/0001/images/0001.png"],"show_exhibits":"1","datetime":"1493283465","html_path":"http://192.168.10.158/wdd_second/resource/museum/0001/CHINESE/0001.html","title":"五大道博物馆","is_can_scan":1,"type":1}
     * msg : 操作成功
     */

    private int status;
    /**
     * museum_id : 0001
     * imgs : ["http://192.168.10.158/wdd_second/resource/museum/0001/images/0001.png"]
     * show_exhibits : 1
     * datetime : 1493283465
     * html_path : http://192.168.10.158/wdd_second/resource/museum/0001/CHINESE/0001.html
     * title : 五大道博物馆
     * is_can_scan : 1
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
        private String museum_id;
        private String show_exhibits;
        private String datetime;
        private String html_path;
        private String title;
        private int is_can_scan;
        private int type;
        private List<String> imgs;

        public String getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(String museum_id) {
            this.museum_id = museum_id;
        }

        public String getShow_exhibits() {
            return show_exhibits;
        }

        public void setShow_exhibits(String show_exhibits) {
            this.show_exhibits = show_exhibits;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getHtml_path() {
            return html_path;
        }

        public void setHtml_path(String html_path) {
            this.html_path = html_path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_can_scan() {
            return is_can_scan;
        }

        public void setIs_can_scan(int is_can_scan) {
            this.is_can_scan = is_can_scan;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
