package com.hengda.smart.wuda.m.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class MuseumBean {


    /**
     * status : 1
     * data : [{"id":"0001","title":"五大道博物馆","is_valid":0},{"id":"0002","title":"拜石博物馆","is_valid":0},{"id":"0003","title":"王光英复原展厅","is_valid":0},{"id":"0004","title":"马车游览","is_valid":0},{"id":"0005","title":"自行车游览","is_valid":0},{"id":"0006","title":"非物质文化遗产馆","is_valid":0},{"id":"0007","title":"天津体育博物馆","is_valid":0}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 0001
         * title : 五大道博物馆
         * is_valid : 0
         */

        private String id;
        private String title;
        private int is_valid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(int is_valid) {
            this.is_valid = is_valid;
        }
    }
}
