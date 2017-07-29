package com.hengda.smart.wuda.m.base;/**
 * Created by lenovo on 2017/7/3.
 */

import android.content.SharedPreferences;

import com.hengda.smart.wuda.m.tools.SharedPrefUtil;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/3 10:59
 * 类描述：
 */
public class HD_APP_Config {
    public static final String AUTO_FLAG = "AUTO_FLAG";//自动讲解：false关闭，true开启
    public static final String TEMP_NUM = "TEMP_NUM";//自动讲解：false关闭，true开启
    private static SharedPrefUtil appConfigShare = new SharedPrefUtil(HD_Application.context,
            Contants.SHARED_PREF_NAME);

    public static void setAutoPlay(boolean autoPlay) {
        appConfigShare.setPrefBoolean(AUTO_FLAG, autoPlay);
    }

    public static boolean getAutoPlay() {
        return appConfigShare.getPrefBoolean(AUTO_FLAG,false);
    }

    public static void setTempNum(String tempNum) {
        appConfigShare.setPrefString(TEMP_NUM, tempNum);
    }

    public static String getTempNum() {
        return appConfigShare.getPrefString(TEMP_NUM);
    }
}
