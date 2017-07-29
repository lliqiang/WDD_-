package com.hengda.smart.wuda.m.baidu;/**
 * Created by lenovo on 2017/7/3.
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 创建人：lenovo
 * 创建时间：2017/7/3 15:08
 * 类描述：
 */
public class MyOrientationListener implements SensorEventListener {
    private SensorManager mSensorManager;
    private Context mContext;
    private Sensor mSensor;
    private float lastX;
    private OnOrientationListener mOnOrientationListener;

    public void setmOnOrientationListener(OnOrientationListener mOnOrientationListener) {
        this.mOnOrientationListener = mOnOrientationListener;
    }

    public MyOrientationListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float x = event.values[SensorManager.DATA_X];
            if (Math.abs(x - lastX) > 1.0) {
                if (mOnOrientationListener != null) {
                    mOnOrientationListener.onOrientationChanged(x);
                }
            }

            lastX = x;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public interface OnOrientationListener {
        void onOrientationChanged(float x);
    }

    public void start() {
        mSensorManager = (SensorManager) mContext
                .getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            //获得方向传感器
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }

        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void stop() {
        //停止定位
        mSensorManager.unregisterListener(this);
    }
}
