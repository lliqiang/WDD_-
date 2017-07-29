package com.hengda.smart.wuda.m.tools;

import com.hengda.zwf.commonutil.SDCardUtil;

/**
 * Created by lenovo on 2017/5/9.
 */

public class SDCardTools {

    //    获取默认文件存储目录
    public static String getDefaultFileDir() {
        return SDCardUtil.getSDCardPath() + "Hd_Wu/";
    }
}
