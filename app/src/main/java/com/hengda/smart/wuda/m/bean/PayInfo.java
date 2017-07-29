package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/25.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/25 17:23
 * 类描述：
 */
public class PayInfo {
    /**
     * status : 1
     * data : {"order_sn":"146","wxpayinfo":{"appid":"wxf962871cf36ad562","partnerid":"1486420432","prepayid":"wx20170727092943b1d5c5231f0830346312","package":"Sign=WXPay","noncestr":"SadPre","timestamp":1501118983,"sign":"A5F6FBC43B7D6E266F05E162F5820BF2"}}
     * msg : 操作成功
     */

    private int status;
    /**
     * order_sn : 146
     * wxpayinfo : {"appid":"wxf962871cf36ad562","partnerid":"1486420432","prepayid":"wx20170727092943b1d5c5231f0830346312","package":"Sign=WXPay","noncestr":"SadPre","timestamp":1501118983,"sign":"A5F6FBC43B7D6E266F05E162F5820BF2"}
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
        private String order_sn;
        /**
         * appid : wxf962871cf36ad562
         * partnerid : 1486420432
         * prepayid : wx20170727092943b1d5c5231f0830346312
         * package : Sign=WXPay
         * noncestr : SadPre
         * timestamp : 1501118983
         * sign : A5F6FBC43B7D6E266F05E162F5820BF2
         */

        private WxpayinfoBean wxpayinfo;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public WxpayinfoBean getWxpayinfo() {
            return wxpayinfo;
        }

        public void setWxpayinfo(WxpayinfoBean wxpayinfo) {
            this.wxpayinfo = wxpayinfo;
        }

        public static class WxpayinfoBean {
            private String appid;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String noncestr;
            private int timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }


//
//    /**
//     * status : 1
//     * data : {"order_sn":"146","wxpayinfo":{"appid":"wxf962871cf36ad562","partnerid":"1486420432","prepayid":"wx20170727092943b1d5c5231f0830346312","package":"Sign=WXPay","noncestr":"SadPre","timestamp":1501118983,"sign":"A5F6FBC43B7D6E266F05E162F5820BF2"}}
//     * msg : 操作成功
//     */
//
//    private int status;
//    /**
//     * order_sn : 146
//     * wxpayinfo : {"appid":"wxf962871cf36ad562","partnerid":"1486420432","prepayid":"wx20170727092943b1d5c5231f0830346312","package":"Sign=WXPay","noncestr":"SadPre","timestamp":1501118983,"sign":"A5F6FBC43B7D6E266F05E162F5820BF2"}
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
//        private String order_sn;
//        /**
//         * appid : wxf962871cf36ad562
//         * partnerid : 1486420432
//         * prepayid : wx20170727092943b1d5c5231f0830346312
//         * package : Sign=WXPay
//         * noncestr : SadPre
//         * timestamp : 1501118983
//         * sign : A5F6FBC43B7D6E266F05E162F5820BF2
//         */
//
//        private WxpayinfoBean wxpayinfo;
//
//        public String getOrder_sn() {
//            return order_sn;
//        }
//
//        public void setOrder_sn(String order_sn) {
//            this.order_sn = order_sn;
//        }
//
//        public WxpayinfoBean getWxpayinfo() {
//            return wxpayinfo;
//        }
//
//        public void setWxpayinfo(WxpayinfoBean wxpayinfo) {
//            this.wxpayinfo = wxpayinfo;
//        }
//
//        public static class WxpayinfoBean implements Serializable{
//            private String appid;
//            private String partnerid;
//            private String prepayid;
//            @SerializedName("package")
//            private String packageX;
//            private String noncestr;
//            private int timestamp;
//            private String sign;
//
//            public String getAppid() {
//                return appid;
//            }
//
//            public void setAppid(String appid) {
//                this.appid = appid;
//            }
//
//            public String getPartnerid() {
//                return partnerid;
//            }
//
//            public void setPartnerid(String partnerid) {
//                this.partnerid = partnerid;
//            }
//
//            public String getPrepayid() {
//                return prepayid;
//            }
//
//            public void setPrepayid(String prepayid) {
//                this.prepayid = prepayid;
//            }
//
//            public String getPackageX() {
//                return packageX;
//            }
//
//            public void setPackageX(String packageX) {
//                this.packageX = packageX;
//            }
//
//            public String getNoncestr() {
//                return noncestr;
//            }
//
//            public void setNoncestr(String noncestr) {
//                this.noncestr = noncestr;
//            }
//
//            public int getTimestamp() {
//                return timestamp;
//            }
//
//            public void setTimestamp(int timestamp) {
//                this.timestamp = timestamp;
//            }
//
//            public String getSign() {
//                return sign;
//            }
//
//            public void setSign(String sign) {
//                this.sign = sign;
//            }
//        }
//    }
}
