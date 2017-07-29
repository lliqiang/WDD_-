package com.hengda.smart.wuda.m.bean;/**
 * Created by lenovo on 2017/7/25.
 */

import java.io.Serializable;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/25 17:39
 * 类描述：
 */
public class AlipayBean implements Serializable {

    /**
     * status : 1
     * data : {"order_sn":"1701690041","payinfo":"app_id=2017010904947813&biz_content=%7B%22out_trade_no%22%3A%221701690041%22%2C%22subject%22%3A%22%5Cu60a6%5Cu8bfb%5Cu4e0a%5Cu6d77+-+%5Cu6d4b%5Cu8bd5%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Flocalhost%2Fshghg%2Falinotify&sign=Z%2Fia79DfPjpG%2Fzt0xEamB%2Fjrf%2FvcwLK32a38m6gq%2BPjsxWWRn0L5FsUc6kRYeqGT%2B53GuvJFrbueOTP5vHGHj1z5YvYH4wfkXWUMz4Te3XTdFkwWUjEywzeaac%2BLs7bE5BbiSa3OUwIBoFrf6xfLh%2FvhV3hLLcA0uULtqLuewsSkiyTLewdyXsND4NASPK5dL8BbjOWsXOkWKdS6eQYOCV1PbAYWGYcPowm3aWHhrX3EbSHqj%2BBX1lqxsHPyWJO7ZBU9YxNuI1bSZ3F1%2FHb4m%2BCpU7iJb6xuKeS15snANkfYmame9j%2FBZTHQdTrnD%2F%2FTv7qOyzzvv7WYajHCtc9oYw%3D%3D&sign_type=RSA2&timestamp=2017-01-18+15%3A54%3A36&version=1.0"}
     * msg :
     */

    private int status;
    /**
     * order_sn : 1701690041
     * payinfo : app_id=2017010904947813&biz_content=%7B%22out_trade_no%22%3A%221701690041%22%2C%22subject%22%3A%22%5Cu60a6%5Cu8bfb%5Cu4e0a%5Cu6d77+-+%5Cu6d4b%5Cu8bd5%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Flocalhost%2Fshghg%2Falinotify&sign=Z%2Fia79DfPjpG%2Fzt0xEamB%2Fjrf%2FvcwLK32a38m6gq%2BPjsxWWRn0L5FsUc6kRYeqGT%2B53GuvJFrbueOTP5vHGHj1z5YvYH4wfkXWUMz4Te3XTdFkwWUjEywzeaac%2BLs7bE5BbiSa3OUwIBoFrf6xfLh%2FvhV3hLLcA0uULtqLuewsSkiyTLewdyXsND4NASPK5dL8BbjOWsXOkWKdS6eQYOCV1PbAYWGYcPowm3aWHhrX3EbSHqj%2BBX1lqxsHPyWJO7ZBU9YxNuI1bSZ3F1%2FHb4m%2BCpU7iJb6xuKeS15snANkfYmame9j%2FBZTHQdTrnD%2F%2FTv7qOyzzvv7WYajHCtc9oYw%3D%3D&sign_type=RSA2&timestamp=2017-01-18+15%3A54%3A36&version=1.0
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
        private String order_sn;
        private String payinfo;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPayinfo() {
            return payinfo;
        }

        public void setPayinfo(String payinfo) {
            this.payinfo = payinfo;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "order_sn='" + order_sn + '\'' +
                    ", payinfo='" + payinfo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AlipayBean{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
