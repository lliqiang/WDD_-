package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/22.
 */

/**
 * 创建人：lenovo
 * 创建时间：2017/7/22 10:39
 * 类描述：
 */
public class CountBean {

    /**
     * status : 1
     * data : {"tips":"已有164人使用五大道游客服务中心APP"}
     * msg : 操作成功
     */

    private int status;
    /**
     * tips : 已有164人使用五大道游客服务中心APP
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
        private String tips;

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
