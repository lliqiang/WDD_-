package com.hengda.smart.wuda.m.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/4/12.
 */

public class TrafficBean {


    /**
     * status : 1
     * data : [{"title":"五大道游客服务中心→和平区旅游景点","travel_info":[{"desc":"黄家花园站：9路/831路至中心公园站；951路至荣善西里站\n重庆道站：619路至山东路站","title":"五大道游客服务中心→瓷房子"},{"desc":"黄家花园站：观光2路至鞍山道站\n地铁一号线营口道站至鞍山道站A出口","title":"五大道游客服务中心→静园"},{"desc":"黄家花园站：观光2路至滨江道站\n重庆道站：961路至滨江商厦站；906/954路至滨江道站；","title":"五大道游客服务中心→滨江道商业街"},{"desc":"黄家花园站：观光2路至南市食品街站\n重庆道站：945路至南市食品街站","title":"五大道游客服务中心→南市食品街"}]},{"title":"五大道游客服务中心→天津旅游景点","travel_info":[{"desc":"重庆道站：954路至世纪乐天站；906路至三条石站；\n湖北路站：652路/652路至永乐桥站；\n地铁一号线营口道站至西南角站B出口后，步行2.1公里。","title":"五大道游客服务中心→天津之眼"},{"desc":"重庆道站：954路/619路至东北角站；\n黄家花园站：观光2路至东北角站；\n长沙路站：632路/641路/65路至东北角站\t\n地铁三号线营口道站至天津站换乘地铁二号线东南角站D出口","title":"五大道游客服务中心→古文化街（天后宫）"},{"desc":"长沙路站：675路/865路至慎益大街站；840路至鼓楼站；\n黄家花园站：观光2路至南市食品街站\n地铁一号线营口道站至西南角换乘地铁二号线至鼓楼站","title":"五大道游客服务中心→鼓楼（广东会馆）"},{"desc":"崇仁里站：901路至北安桥下车\n黄家花园站：951路/845路至口腔医院站；\n地铁三号线营口道站至和平路站B出口后，步行1.1公里\n地铁三号线营口道站至天津站换乘地铁二号线建国道站B出口","title":"五大道游客服务中心→海河意式风情街"},{"desc":"黄家花园站：观光2路至会宾园\n外国语大学站：904路至会宾园\n重庆道站：871至会宾园\n地铁三号线营口道站至周邓纪念馆B出口","title":"五大道游客服务中心→周邓纪念馆"},{"desc":"外国语大学站：685路至动物园站；904路至二七一医院站\n地铁三号线营口道站至周邓纪念馆B出口后，步行1.4公里","title":"五大道游客服务中心→天津动物园"},{"desc":"重庆道站：906路至宾馆南道站\n长沙路站：641路至宾馆南道站","title":"五大道游客服务中心→天津自然博物馆"},{"desc":"黄家花园站：831路/951路/902路至佟楼站换乘175路至石家大院站；\n观光2路至南市食品街站换乘824路至石家大院\n地铁三号线营口道站至吴家窑站A2出口，步行至德才里站换乘75路至石家大院站.\n地铁一号线营口道站至西南角站换乘地铁二号线至曹庄站A出口","title":"五大道游客服务中心→天津石家大院"},{"desc":"重庆道站：871路至大光明桥站，换乘津滨轻轨9号线至塘沽站下车，换乘110路极地海洋世界站\n湖北路站：605路至坨地站","title":"五大道游客服务中心→天津极地海洋世界"},{"desc":"重庆道站：871路至大光明桥站，换乘津滨轻轨9号线至塘沽站\n黄家花园站：902路至大光明桥站换乘津滨轻轨9号线至塘沽站","title":"五大道游客服务中心→塘沽洋货市场"},{"desc":"重庆道站：871路至大光明桥站，换乘津滨轻轨9号线至塘沽站下车步行至宏达公寓站换乘133路到航母主题公园站","title":"五大道游客服务中心→天津航母主题公园"}]}]
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

    public static class DataBean {
        /**
         * title : 五大道游客服务中心→和平区旅游景点
         * travel_info : [{"desc":"黄家花园站：9路/831路至中心公园站；951路至荣善西里站\n重庆道站：619路至山东路站","title":"五大道游客服务中心→瓷房子"},{"desc":"黄家花园站：观光2路至鞍山道站\n地铁一号线营口道站至鞍山道站A出口","title":"五大道游客服务中心→静园"},{"desc":"黄家花园站：观光2路至滨江道站\n重庆道站：961路至滨江商厦站；906/954路至滨江道站；","title":"五大道游客服务中心→滨江道商业街"},{"desc":"黄家花园站：观光2路至南市食品街站\n重庆道站：945路至南市食品街站","title":"五大道游客服务中心→南市食品街"}]
         */

        private String title;
        private List<TravelInfoBean> travel_info;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<TravelInfoBean> getTravel_info() {
            return travel_info;
        }

        public void setTravel_info(List<TravelInfoBean> travel_info) {
            this.travel_info = travel_info;
        }

        public static class TravelInfoBean {
            /**
             * desc : 黄家花园站：9路/831路至中心公园站；951路至荣善西里站
             重庆道站：619路至山东路站
             * title : 五大道游客服务中心→瓷房子
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
