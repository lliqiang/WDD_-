package com.hengda.smart.wuda.m.base;

import java.util.List;

/**
 * Created by lenovo on 2017/4/13.
 */

public class SearchBean {


    /**
     * status : 1
     * data : {"web_site":"http://www.tjwudadao.cn","weixin_url":"http://192.168.10.158/wdd/resource/introduction/weixin/index.html","taobao_url":"https://shop553012461.taobao.com/?spm=a230r.7195193.1997079397.2.cuZogN","ticket_url":"http://192.168.10.158/wdd/resource/introduction/ticket.html","parking_url":"http://192.168.10.158/wdd/resource/introduction/waiting/index.html","hotel_url":"http://192.168.10.158/wdd/resource/introduction/waiting/index.html","restaurant_url":"http://192.168.10.158/wdd/resource/introduction/waiting/index.html","leave_msg":"http://192.168.10.158/wdd//index.php?g=mapi&m=Discover&a=leave_msg","comment_info":[{"title":"今天来到了天津著名的旅游景点\u2014\u2014五大道，参观了很多欧式...","time":"2017年4月12号","nick_name":"草率","user_head":"http://192.168.10.158/wdd/resource/users/0001/0001.png","look_num":12,"comment_num":12,"comment_img":"http://192.168.10.158/wdd/resource/comments/0001/comment_1.png"},{"title":"五大道地区作为近现代天津历史的一个体现，蕴藏着丰富的...","time":"2017年4月17号","nick_name":"时间旅行者","user_head":"http://192.168.10.158/wdd/resource/users/0002/0002.png","look_num":12,"comment_num":12,"comment_img":"http://192.168.10.158/wdd/resource/comments/0002/comment_2.png"}]}
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
         * web_site : http://www.tjwudadao.cn
         * weixin_url : http://192.168.10.158/wdd/resource/introduction/weixin/index.html
         * taobao_url : https://shop553012461.taobao.com/?spm=a230r.7195193.1997079397.2.cuZogN
         * ticket_url : http://192.168.10.158/wdd/resource/introduction/ticket.html
         * parking_url : http://192.168.10.158/wdd/resource/introduction/waiting/index.html
         * hotel_url : http://192.168.10.158/wdd/resource/introduction/waiting/index.html
         * restaurant_url : http://192.168.10.158/wdd/resource/introduction/waiting/index.html
         * leave_msg : http://192.168.10.158/wdd//index.php?g=mapi&m=Discover&a=leave_msg
         * comment_info : [{"title":"今天来到了天津著名的旅游景点\u2014\u2014五大道，参观了很多欧式...","time":"2017年4月12号","nick_name":"草率","user_head":"http://192.168.10.158/wdd/resource/users/0001/0001.png","look_num":12,"comment_num":12,"comment_img":"http://192.168.10.158/wdd/resource/comments/0001/comment_1.png"},{"title":"五大道地区作为近现代天津历史的一个体现，蕴藏着丰富的...","time":"2017年4月17号","nick_name":"时间旅行者","user_head":"http://192.168.10.158/wdd/resource/users/0002/0002.png","look_num":12,"comment_num":12,"comment_img":"http://192.168.10.158/wdd/resource/comments/0002/comment_2.png"}]
         */

        private String web_site;
        private String weixin_url;
        private String taobao_url;
        private String ticket_url;
        private String parking_url;
        private String hotel_url;
        private String restaurant_url;
        private String leave_msg;
        private List<CommentInfoBean> comment_info;

        public String getWeb_site() {
            return web_site;
        }

        public void setWeb_site(String web_site) {
            this.web_site = web_site;
        }

        public String getWeixin_url() {
            return weixin_url;
        }

        public void setWeixin_url(String weixin_url) {
            this.weixin_url = weixin_url;
        }

        public String getTaobao_url() {
            return taobao_url;
        }

        public void setTaobao_url(String taobao_url) {
            this.taobao_url = taobao_url;
        }

        public String getTicket_url() {
            return ticket_url;
        }

        public void setTicket_url(String ticket_url) {
            this.ticket_url = ticket_url;
        }

        public String getParking_url() {
            return parking_url;
        }

        public void setParking_url(String parking_url) {
            this.parking_url = parking_url;
        }

        public String getHotel_url() {
            return hotel_url;
        }

        public void setHotel_url(String hotel_url) {
            this.hotel_url = hotel_url;
        }

        public String getRestaurant_url() {
            return restaurant_url;
        }

        public void setRestaurant_url(String restaurant_url) {
            this.restaurant_url = restaurant_url;
        }

        public String getLeave_msg() {
            return leave_msg;
        }

        public void setLeave_msg(String leave_msg) {
            this.leave_msg = leave_msg;
        }

        public List<CommentInfoBean> getComment_info() {
            return comment_info;
        }

        public void setComment_info(List<CommentInfoBean> comment_info) {
            this.comment_info = comment_info;
        }

        public static class CommentInfoBean {
            /**
             * title : 今天来到了天津著名的旅游景点——五大道，参观了很多欧式...
             * time : 2017年4月12号
             * nick_name : 草率
             * user_head : http://192.168.10.158/wdd/resource/users/0001/0001.png
             * look_num : 12
             * comment_num : 12
             * comment_img : http://192.168.10.158/wdd/resource/comments/0001/comment_1.png
             */

            private String title;
            private String time;
            private String nick_name;
            private String user_head;
            private int look_num;
            private int comment_num;
            private String comment_img;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getUser_head() {
                return user_head;
            }

            public void setUser_head(String user_head) {
                this.user_head = user_head;
            }

            public int getLook_num() {
                return look_num;
            }

            public void setLook_num(int look_num) {
                this.look_num = look_num;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public String getComment_img() {
                return comment_img;
            }

            public void setComment_img(String comment_img) {
                this.comment_img = comment_img;
            }
        }
    }
}
