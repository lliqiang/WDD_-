package com.hengda.smart.wuda.m.bean;

/**
 * Created by lenovo on 2017/4/7.
 */

public class AppUpdata {

    /**
     * status : 2002
     * msg : 有新的版本
     * versionInfo : {"versionNo":"2.0","versionName":"第二版","versionUrl":"http:/www.baidu.com","versionLog":"爱更不更"}
     */

    private String status;
    private String msg;
    private VersionInfoBean versionInfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VersionInfoBean getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoBean versionInfo) {
        this.versionInfo = versionInfo;
    }

    public static class VersionInfoBean {
        /**
         * versionNo : 2.0
         * versionName : 第二版
         * versionUrl : http:/www.baidu.com
         * versionLog : 爱更不更
         */

        private String versionNo;
        private String versionName;
        private String versionUrl;
        private String versionLog;

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }

        public String getVersionLog() {
            return versionLog;
        }

        public void setVersionLog(String versionLog) {
            this.versionLog = versionLog;
        }
    }
}
