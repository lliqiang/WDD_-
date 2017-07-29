package com.hengda.smart.wuda.m.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */

public class NewsBean {

    /**
     * status : 1
     * data : {"img_list":["http://47.93.81.30/wdd/resource/news/0001.png","http://47.93.81.30/wdd//resource/news/0002.png"],"news_list":[{"title":"时事逻辑","list_img":"http://47.93.81.30/wdd/resource/news/0001/images/0001.jpg","date":"2017-04-06","news_id":"0001"},{"title":"312312","list_img":"http://47.93.81.30/wdd/resource/museum/31231/images/31231_list.jpg","date":"1970-01-01","news_id":"31231"}]}
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
        private List<String> img_list;
        private List<NewsListBean> news_list;

        public List<String> getImg_list() {
            return img_list;
        }

        public void setImg_list(List<String> img_list) {
            this.img_list = img_list;
        }

        public List<NewsListBean> getNews_list() {
            return news_list;
        }

        public void setNews_list(List<NewsListBean> news_list) {
            this.news_list = news_list;
        }

        public static class NewsListBean implements Serializable{
            /**
             * title : 时事逻辑
             * list_img : http://47.93.81.30/wdd/resource/news/0001/images/0001.jpg
             * date : 2017-04-06
             * news_id : 0001
             */

            private String title;
            private String list_img;
            private String date;
            private String news_id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getList_img() {
                return list_img;
            }

            public void setList_img(String list_img) {
                this.list_img = list_img;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getNews_id() {
                return news_id;
            }

            public void setNews_id(String news_id) {
                this.news_id = news_id;
            }
        }
    }
}
