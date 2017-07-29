package com.hengda.smart.wuda.m.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/8.
 */
public class BeaconModel implements Serializable {
    private int major;
    private int minor;
    private double distance;
    private int rssi;

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }


    @Override
    public String toString() {
        return "BeaconModel{" +
                "major=" + major +
                ", minor=" + minor +
                ", distance=" + distance +
                ", rssi=" + rssi +
                '}';
    }
}
