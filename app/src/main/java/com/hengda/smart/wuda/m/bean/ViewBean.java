package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/4.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/4 8:35
 * 类描述：
 */
public class ViewBean  implements Serializable{


    /**
     * status : 1
     * data : {"feature_info":[{"feature_id":"0001","title":"景点名称1","mp3_path":"http://192.168.10.158/wdd_second/resource/feature/0001/CHINESE/0001.mp3","img":"http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_img.png","poi_img":"http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_poi.png","big_poi_img":"http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_poi.png","list_img":"http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_list.png","jingdu":"39.093449","weidu":"117.135027","radius":"250"},{"feature_id":"0002","title":"十华里","mp3_path":"http://192.168.10.158/wdd_second/resource/feature/0002/CHINESE/0002.mp3","img":"http://192.168.10.158/wdd_second/resource/feature/0002/images/0002_img.png","poi_img":"http://192.168.10.158/wdd_second/resource/feature/0002/images/0002_poi.png","big_poi_img":"http://192.168.10.158/wdd_second/resource/feature/0002/images/0002_poi.png","list_img":"http://192.168.10.158/wdd_second/resource/feature/0002/images/0002_list.png","jingdu":"39.092357","weidu":"117.132745","radius":"251"}],"recommand_lines":[{"line_id":"91","title":"推荐一路线","position_info":[{"jingdu":"39.122171","weidu":"117.184019"},{"jingdu":"39.101791","weidu":"117.213771"},{"jingdu":"39.082189","weidu":"117.151106"},{"jingdu":"39.112094","weidu":"117.151537"},{"jingdu":"39.114109","weidu":"117.127965"}]},{"line_id":"94","title":"推荐路线2","position_info":[{"jingdu":"39.099551","weidu":"117.205866"},{"jingdu":"39.089919","weidu":"117.119341"},{"jingdu":"39.112318","weidu":"117.150099"},{"jingdu":"39.100447","weidu":"117.167922"}]}]}
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

    public static class DataBean implements Serializable{
        /**
         * feature_id : 0001
         * title : 景点名称1
         * mp3_path : http://192.168.10.158/wdd_second/resource/feature/0001/CHINESE/0001.mp3
         * img : http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_img.png
         * poi_img : http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_poi.png
         * big_poi_img : http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_poi.png
         * list_img : http://192.168.10.158/wdd_second/resource/feature/0001/images/0001_list.png
         * jingdu : 39.093449
         * weidu : 117.135027
         * radius : 250
         */

        private List<FeatureInfoBean> feature_info;
        /**
         * line_id : 91
         * title : 推荐一路线
         * position_info : [{"jingdu":"39.122171","weidu":"117.184019"},{"jingdu":"39.101791","weidu":"117.213771"},{"jingdu":"39.082189","weidu":"117.151106"},{"jingdu":"39.112094","weidu":"117.151537"},{"jingdu":"39.114109","weidu":"117.127965"}]
         */

        private List<RecommandLinesBean> recommand_lines;

        public List<FeatureInfoBean> getFeature_info() {
            return feature_info;
        }

        public void setFeature_info(List<FeatureInfoBean> feature_info) {
            this.feature_info = feature_info;
        }

        public List<RecommandLinesBean> getRecommand_lines() {
            return recommand_lines;
        }

        public void setRecommand_lines(List<RecommandLinesBean> recommand_lines) {
            this.recommand_lines = recommand_lines;
        }

        public static class FeatureInfoBean implements Serializable{
            private String feature_id;
            private String title;
            private String mp3_path;
            private String img;
            private String poi_img;
            private String big_poi_img;
            private String list_img;
            private String jingdu;
            private String weidu;
            private String radius;

            public String getFeature_id() {
                return feature_id;
            }

            public void setFeature_id(String feature_id) {
                this.feature_id = feature_id;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPoi_img() {
                return poi_img;
            }

            public void setPoi_img(String poi_img) {
                this.poi_img = poi_img;
            }

            public String getBig_poi_img() {
                return big_poi_img;
            }

            public void setBig_poi_img(String big_poi_img) {
                this.big_poi_img = big_poi_img;
            }

            public String getList_img() {
                return list_img;
            }

            public void setList_img(String list_img) {
                this.list_img = list_img;
            }

            public String getJingdu() {
                return jingdu;
            }

            public void setJingdu(String jingdu) {
                this.jingdu = jingdu;
            }

            public String getWeidu() {
                return weidu;
            }

            public void setWeidu(String weidu) {
                this.weidu = weidu;
            }

            public String getRadius() {
                return radius;
            }

            public void setRadius(String radius) {
                this.radius = radius;
            }
        }

        public static class RecommandLinesBean implements Serializable{
            private String line_id;
            private String title;
            /**
             * jingdu : 39.122171
             * weidu : 117.184019
             */

            private List<PositionInfoBean> position_info;

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<PositionInfoBean> getPosition_info() {
                return position_info;
            }

            public void setPosition_info(List<PositionInfoBean> position_info) {
                this.position_info = position_info;
            }

            public static class PositionInfoBean implements Serializable{
                private String jingdu;
                private String weidu;

                public String getJingdu() {
                    return jingdu;
                }

                public void setJingdu(String jingdu) {
                    this.jingdu = jingdu;
                }

                public String getWeidu() {
                    return weidu;
                }

                public void setWeidu(String weidu) {
                    this.weidu = weidu;
                }
            }
        }
    }
}
