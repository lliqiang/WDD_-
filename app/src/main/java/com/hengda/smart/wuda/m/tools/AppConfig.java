package com.hengda.smart.wuda.m.tools;

import android.text.TextUtils;

import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.zwf.commonutil.FileUtils;
import com.hengda.zwf.commonutil.SDCardUtil;


/**
 * Created by lenovo on 2017/2/28.
 */

public class AppConfig {
    //    App配置文件名称
    private static final String SHARED_PREF_NAME_APP = "SHARED_PREF_NAME_APP";
    //    数据库文件名称
    public static final String DB_FILE_NAME = "Filemanage.sqlite";

    //    SharedPref字段
    public static final String LANGUAGE = "LANGUAGE";//当前语种
    public static final String AUTO_FLAG = "AUTO_FLAG";//自动讲解：0关闭，1开启
    public static final String AUTO_MODE = "AUTO_MODE";//讲解方式：0隔一，1连续
    public static final String LOGIN_STATE = "LOGIN_STATE";//智慧服务：0关闭，1开启
    public static final String STC_MODE = "STC_MODE";//报警方式：0直接报警，1间接报警
    public static final String RECEIVE_NO_MODE = "RECEIVE_NO_MODE";//收号方式：0蓝牙，1RFID，2混合
    public static final String SCREEN_MODE = "SCREEN_MODE";//节能模式：0关闭，1开启
    public static final String POWER_MODE = "POWER_MODE";//关机权限：0禁止，1允许
    public static final String POWER_PERMI = "POWER_PERMI";//禁止关机下是否获取到关机权限：0无，1有
    public static final String IP_PORT = "IP_PORT";//服务器IP和端口
    public static final String PASSWORD = "PASSWORD";//管理员密码
    public static final String NICKNAME = "NICKNAME";//管理员密码
    public static final String PHONENUM = "PHONENUM";//管理员密码
    public static final String TOKEN = "TOKEN";//RSSI门限

    private static SharedPrefUtil appConfig = new SharedPrefUtil(HD_Application.getInstance(), SHARED_PREF_NAME_APP);

    public static void setLanguage(String language) {
        appConfig.setPrefString(LANGUAGE, language);
    }

    public static String getLanguage() {
        return appConfig.getPrefString(LANGUAGE, Contants.Current_Lan);
    }

    public static void setDeviceNo(String deviceNo) {
//        appConfig.setPrefString(Contants.DEVICE_NO, deviceNo);
        FileUtils.writeStringToFile(SDCardTools.getDefaultFileDir() + "DeviceNo.txt", deviceNo, false);
    }

    public static String getDeviceNo() {
//        return appConfig.getPrefString(Contants.DEVICE_NO, "");
        StringBuilder deviceNo = FileUtils.readStringFromFile(SDCardTools.getDefaultFileDir() + "DeviceNo.txt", "UTF-8");
        return TextUtils.isEmpty(deviceNo) ? Contants.DEFAULT_DEVICE_NO : deviceNo.toString();
    }

    public static void setNickName(String nickName) {
        appConfig.setPrefString(NICKNAME, nickName);
    }

    public static String getNickname() {
        return appConfig.getPrefString(NICKNAME, "");
    }

    public static void setPhoneNum(String nickName) {
        appConfig.setPrefString(PHONENUM, nickName);
    }

    public static String getPhoneNum() {
        return appConfig.getPrefString(PHONENUM, "");
    }



    public static void setPassword(String password) {
        appConfig.setPrefString(PASSWORD, password);
    }

    public static String getPassword() {
        return appConfig.getPrefString(PASSWORD, "");
    }

    //Token
    public static void setToken(String token) {
        appConfig.setPrefString(TOKEN, token);
    }
    public static String getToken() {
        return appConfig.getPrefString(TOKEN);
    }


    public static void setLoginState(boolean flag) {
        appConfig.setPrefBoolean(LOGIN_STATE, flag);
    }
    public static boolean getLoginState() {
        return appConfig.getPrefBoolean(LOGIN_STATE);
    }

    public static void setAutoFlag(int autoFlag) {
        appConfig.setPrefInt(AUTO_FLAG, autoFlag);
    }

    public static int getAutoFlag() {
        return appConfig.getPrefInt(AUTO_FLAG, 1);
    }

    public static void setAutoMode(int autoMode) {
        appConfig.setPrefInt(AUTO_MODE, autoMode);
    }

    public static int getAutoMode() {
        return appConfig.getPrefInt(AUTO_MODE, 0);
    }

    public static void setSTCMode(int flag) {
        appConfig.setPrefInt(STC_MODE, flag);
    }

    public static int getSTCMode() {
        return appConfig.getPrefInt(STC_MODE, 1);
    }

    public static void setReceiveNoMode(int receiveNoMode) {
        appConfig.setPrefInt(RECEIVE_NO_MODE, receiveNoMode);
    }

    public static int getReceiveNoMode() {
        return appConfig.getPrefInt(RECEIVE_NO_MODE, 1);
    }

    public static void setScreenMode(int flag) {
        appConfig.setPrefInt(SCREEN_MODE, flag);
    }

    public static int getScreenMode() {
        return appConfig.getPrefInt(SCREEN_MODE, 0);
    }

    public static void setPowerMode(int flag) {
        appConfig.setPrefInt(POWER_MODE, flag);
    }

    public static int getPowerMode() {
        return appConfig.getPrefInt(POWER_MODE, 0);
    }

    public static void setPowerPermi(int flag) {
        appConfig.setPrefInt(POWER_PERMI, flag);
    }

    public static int getPowerPermi() {
        return appConfig.getPrefInt(POWER_PERMI, 0);
    }

    public static void setDefaultIpPort(String ipPort) {
        appConfig.setPrefString(IP_PORT, ipPort);
    }

    public static String getDefaultIpPort() {
        return appConfig.getPrefString(IP_PORT, "");
    }



    //    获取数据库文件路径
    public static String getDbFilePath() {
        return getDefaultFileDir() + DB_FILE_NAME;
    }

    //    获取地图文件路径
    public static String getMapFilePath() {
        return getDefaultFileDir() + getLanguage() + "/map";
    }

    public static void clear() {
        appConfig.clearPreference();
    }

    //    获取默认文件存储目录
    public static String getDefaultFileDir() {
        return SDCardUtil.getSDCardPath() + "Hd_Wu/";
    }
    public static String getVoicePath(String fileNo){
        return getDefaultFileDir()+"cache/"+fileNo+".mp3";
    }
}
