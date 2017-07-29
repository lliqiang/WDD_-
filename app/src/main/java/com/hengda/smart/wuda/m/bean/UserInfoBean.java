package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/20.
 */

/**
 * 创建人：lenovo
 * 创建时间：2017/7/20 10:25
 * 类描述：
 */
public class UserInfoBean {

    /**
     * status : 1
     * data : {"device_id":"AND0000001144","nick_name":"","avatar":""}
     * msg : 操作成功
     */

    private int status;
    /**
     * device_id : AND0000001144
     * nick_name :
     * avatar :
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
        private String device_id;
        private String nick_name;
        private String avatar;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "device_id='" + device_id + '\'' +
                    ", nick_name='" + nick_name + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}

