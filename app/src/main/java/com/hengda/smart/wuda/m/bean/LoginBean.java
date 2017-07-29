package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/19.
 */

import java.io.Serializable;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/19 11:14
 * 类描述：
 */
public class LoginBean implements Serializable{
    /**
     * status : 1
     * data : {"device_id":"AND0000001188","nick_name":"曾经的曾经","avatar":"http://qzapp.qlogo.cn/qzapp/1106000781/E5423D043B9391E26E4E544EC65560EE/30","youhuiquan_info":{"is_send":0,"price":0,"name":"","desc":"","expire_datetime":""},"token":"Y25SeWRISjBDVUZPUkRBd01EQXdNREV4T0RnSk1UVXdNVEl3TkRjME53PT1SdmtHN0IyUA=="}
     * msg : 登录成功
     */

    private int status;
    /**
     * device_id : AND0000001188
     * nick_name : 曾经的曾经
     * avatar : http://qzapp.qlogo.cn/qzapp/1106000781/E5423D043B9391E26E4E544EC65560EE/30
     * youhuiquan_info : {"is_send":0,"price":0,"name":"","desc":"","expire_datetime":""}
     * token : Y25SeWRISjBDVUZPUkRBd01EQXdNREV4T0RnSk1UVXdNVEl3TkRjME53PT1SdmtHN0IyUA==
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
        private String device_id;
        private String nick_name;
        private String avatar;
        /**
         * is_send : 0
         * price : 0
         * name :
         * desc :
         * expire_datetime :
         */

        private YouhuiquanInfoBean youhuiquan_info;
        private String token;

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

        public YouhuiquanInfoBean getYouhuiquan_info() {
            return youhuiquan_info;
        }

        public void setYouhuiquan_info(YouhuiquanInfoBean youhuiquan_info) {
            this.youhuiquan_info = youhuiquan_info;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class YouhuiquanInfoBean implements Serializable{
            private int is_send;
            private double price;
            private String name;
            private String desc;
            private String expire_datetime;

            public int getIs_send() {
                return is_send;
            }

            public void setIs_send(int is_send) {
                this.is_send = is_send;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getExpire_datetime() {
                return expire_datetime;
            }

            public void setExpire_datetime(String expire_datetime) {
                this.expire_datetime = expire_datetime;
            }
        }
    }


//    /**
//     * status : 1
//     * data : {"device_id":"AND0000001126","nick_name":"测试","avatar":"","youhuiquan_info":{"is_send":0,"price":0,"name":"","desc":"","expire_datetime":""},"token":"YkhFeE1qTTBOVFlKUVU1RU1EQXdNREF3TVRFeU5na3hOVEF3TlRJeE5qTXlSdmtHN0IyUA=="}
//     * msg : 登录成功
//     */
//
//    private int status;
//    /**
//     * device_id : AND0000001126
//     * nick_name : 测试
//     * avatar :
//     * youhuiquan_info : {"is_send":0,"price":0,"name":"","desc":"","expire_datetime":""}
//     * token : YkhFeE1qTTBOVFlKUVU1RU1EQXdNREF3TVRFeU5na3hOVEF3TlRJeE5qTXlSdmtHN0IyUA==
//     */
//
//    private DataBean data;
//    private String msg;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public static class DataBean implements Serializable{
//        private String device_id;
//        private String nick_name;
//        private String avatar;
//        /**
//         * is_send : 0
//         * price : 0
//         * name :
//         * desc :
//         * expire_datetime :
//         */
//
//        private YouhuiquanInfoBean youhuiquan_info;
//        private String token;
//
//        public String getDevice_id() {
//            return device_id;
//        }
//
//        public void setDevice_id(String device_id) {
//            this.device_id = device_id;
//        }
//
//        public String getNick_name() {
//            return nick_name;
//        }
//
//        public void setNick_name(String nick_name) {
//            this.nick_name = nick_name;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public YouhuiquanInfoBean getYouhuiquan_info() {
//            return youhuiquan_info;
//        }
//
//        public void setYouhuiquan_info(YouhuiquanInfoBean youhuiquan_info) {
//            this.youhuiquan_info = youhuiquan_info;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//
//        public static class YouhuiquanInfoBean implements Serializable{
//            private int is_send;
//            private int price;
//            private String name;
//            private String desc;
//            private String expire_datetime;
//
//            public int getIs_send() {
//                return is_send;
//            }
//
//            public void setIs_send(int is_send) {
//                this.is_send = is_send;
//            }
//
//            public int getPrice() {
//                return price;
//            }
//
//            public void setPrice(int price) {
//                this.price = price;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getExpire_datetime() {
//                return expire_datetime;
//            }
//
//            public void setExpire_datetime(String expire_datetime) {
//                this.expire_datetime = expire_datetime;
//            }
//        }
//    }
}
