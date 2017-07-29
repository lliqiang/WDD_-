package com.hengda.smart.wuda.m.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/14.
 * 新闻详细信息
 */

public class NewsDetailsBean {

    /**
     * status : 1
     * data : {"title":"时事逻辑","desc":"<p>时事内容<br/><\/p>  ","html_path":"http://192.168.10.158/wdd/resource/news/0001/CHINESE/0001.html","imgs":["http://192.168.10.158/wdd/resource/news/0001/images/0001.png"],"list_img":"http://192.168.10.158/wdd/resource/news/0001/images/0001.jpg","datetime":"2017-04-13","look_num":"0"}
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
         * title : 时事逻辑
         * desc : <p>时事内容<br/></p>
         * html_path : http://192.168.10.158/wdd/resource/news/0001/CHINESE/0001.html
         * imgs : ["http://192.168.10.158/wdd/resource/news/0001/images/0001.png"]
         * list_img : http://192.168.10.158/wdd/resource/news/0001/images/0001.jpg
         * datetime : 2017-04-13
         * look_num : 0
         */

        private String title;
        private String desc;
        private String html_path;
        private String list_img;
        private String datetime;
        private String look_num;
        private List<String> imgs;

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

        public String getHtml_path() {
            return html_path;
        }

        public void setHtml_path(String html_path) {
            this.html_path = html_path;
        }

        public String getList_img() {
            return list_img;
        }

        public void setList_img(String list_img) {
            this.list_img = list_img;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getLook_num() {
            return look_num;
        }

        public void setLook_num(String look_num) {
            this.look_num = look_num;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
