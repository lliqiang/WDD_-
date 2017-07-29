package com.hengda.smart.wuda.m.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/15.
 */

public class SceneBean {
    /**
     * status : 1
     * data : {"scene_info":{"scene_id":"0001","height":"1523","width":"1235","title":"场景0001","img":"http://192.168.10.158/wdd/resource/scene/0001/images/0001.png"},"exhibit_info":[{"exhibit_id":"0001","axis_y":"540","axis_x":"1120","img":"http://192.168.10.158/wdd/resource/exhibit/0001/images/0001_route.png","title":"展品0001","mp3_path":""}]}
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
         * scene_info : {"scene_id":"0001","height":"1523","width":"1235","title":"场景0001","img":"http://192.168.10.158/wdd/resource/scene/0001/images/0001.png"}
         * exhibit_info : [{"exhibit_id":"0001","axis_y":"540","axis_x":"1120","img":"http://192.168.10.158/wdd/resource/exhibit/0001/images/0001_route.png","title":"展品0001","mp3_path":""}]
         */

        private SceneInfoBean scene_info;
        private List<ExhibitInfoBean> exhibit_info;

        public SceneInfoBean getScene_info() {
            return scene_info;
        }

        public void setScene_info(SceneInfoBean scene_info) {
            this.scene_info = scene_info;
        }

        public List<ExhibitInfoBean> getExhibit_info() {
            return exhibit_info;
        }

        public void setExhibit_info(List<ExhibitInfoBean> exhibit_info) {
            this.exhibit_info = exhibit_info;
        }

        public static class SceneInfoBean {
            /**
             * scene_id : 0001
             * height : 1523
             * width : 1235
             * title : 场景0001
             * img : http://192.168.10.158/wdd/resource/scene/0001/images/0001.png
             */

            private String scene_id;
            private String height;
            private String width;
            private String title;
            private String img;

            public String getScene_id() {
                return scene_id;
            }

            public void setScene_id(String scene_id) {
                this.scene_id = scene_id;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class ExhibitInfoBean {
            /**
             * exhibit_id : 0001
             * axis_y : 540
             * axis_x : 1120
             * img : http://192.168.10.158/wdd/resource/exhibit/0001/images/0001_route.png
             * title : 展品0001
             * mp3_path :
             */

            private String exhibit_id;
            private String axis_y;
            private String axis_x;
            private String img;
            private String title;
            private String mp3_path;

            public String getExhibit_id() {
                return exhibit_id;
            }

            public void setExhibit_id(String exhibit_id) {
                this.exhibit_id = exhibit_id;
            }

            public String getAxis_y() {
                return axis_y;
            }

            public void setAxis_y(String axis_y) {
                this.axis_y = axis_y;
            }

            public String getAxis_x() {
                return axis_x;
            }

            public void setAxis_x(String axis_x) {
                this.axis_x = axis_x;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMp3_path() {
                return mp3_path;
            }

            public void setMp3_path(String mp3_path) {
                this.mp3_path = mp3_path;
            }
        }
    }
}
