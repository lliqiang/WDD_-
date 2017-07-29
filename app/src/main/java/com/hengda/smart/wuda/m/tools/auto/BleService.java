package com.hengda.smart.wuda.m.tools.auto;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.hengda.smart.wuda.m.bean.BeaconModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/10.
 */

public class BleService extends Service{
    private int maxKey = 0;
    private List<BeaconModel> beaconModels = new ArrayList<BeaconModel>();
    private BluetoothAdapter mBluetoothAdapter;
    private List<Integer> ints = new ArrayList<>();
    private boolean mScanning;
    private static final int RESTAR_BLE = 1;
    private static int DEFAULT_SIZE = 25;
    private static int CURRENT_SIZE = 25;

    private SensorManager mSensorManager;
    double[] gravity = new double[3];
    double[] linear_acceleration = new double[3];
    private int count = 0;
    private double acc;
    private double v;
    private boolean isContimue = true;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressLint("NewApi")
    @Override
    public void onCreate() {
        super.onCreate();
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return;
        }
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            return;
        } else {
            if (mBluetoothAdapter.getScanMode() == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
                CURRENT_SIZE = DEFAULT_SIZE * 40 / 50;
            if (!mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.enable();
            mHandler.sendEmptyMessage(RESTAR_BLE);
        }

    }


    @SuppressLint("NewApi")
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESTAR_BLE:
                    scanLeDevice(false);
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    scanLeDevice(true);
                    sendEmptyMessageDelayed(RESTAR_BLE, 1000 * 60 * 2);
                    break;
            }
        }
    };

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            int startByte = 2;
            boolean patternFound = false;
            while (startByte <= 5) {
                if (((int) scanRecord[startByte + 2] & 0xff) == 0x02 && ((int) scanRecord[startByte + 3] & 0xff) == 0x15) {
                    patternFound = true;
                    break;
                }
                startByte++;
            }
            if (patternFound) {
                byte[] uuidBytes = new byte[16];
                System.arraycopy(scanRecord, startByte + 4, uuidBytes, 0, 16);
                int major = (scanRecord[startByte + 20] & 0xff) * 0x100
                        + (scanRecord[startByte + 21] & 0xff);
                int minor = (scanRecord[startByte + 22] & 0xff) * 0x100
                        + (scanRecord[startByte + 23] & 0xff);
                int txPower = (scanRecord[startByte + 24]);
                final BeaconModel beaconModel = new BeaconModel();
                beaconModel.setMajor(major);
                beaconModel.setMinor(minor);
                beaconModel.setRssi(rssi);
                beaconModel.setDistance(calculateAccuracy(txPower, rssi));
                if (!beaconModels.isEmpty()) {
                    List<BeaconModel> tempB = new ArrayList<BeaconModel>();
                    for (BeaconModel b : beaconModels) {
                        if (b.getMajor() == beaconModel.getMajor()) {
                            tempB.add(b);
                        }
                    }
                    beaconModels.removeAll(tempB);
                    beaconModels.add(beaconModel);
                    Collections.sort(beaconModels, comp);
//                    EventBus.getDefault().post(new BleList(beaconModels));
                    for(int i = 0;i<beaconModels.size();i++){
                        Log.e("-----",beaconModels.get(i).getMajor()+"----"+beaconModels.get(i).getMinor());
                    }


//                    if(beaconModels.get(0).getDistance()<Constants.MAP_DISTANCE){
                        ints.add(beaconModels.get(0).getMajor());
                        if (ints.size() != DEFAULT_SIZE) {
                            return;
                        } else {
                            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                            for (int i : ints) {
                                if (null != map.get(i)) {
                                    map.put(i, map.get(i) + 1);
                                } else {
                                    map.put(i, 1);
                                }
                            }
                            int s = BleService.this.FindMaxValue(ints);

//                            if (s >= CURRENT_SIZE && isContimue) {
//                                EventBus.getDefault().post(new AutoNum(maxKey));
//                            }
                            ints.clear();
                            beaconModels.clear();
                        }
//                    }

                } else {
                        beaconModels.add(beaconModel);

                }
            }
        }
    };



    Comparator comp = new Comparator() {
        public int compare(Object o1, Object o2) {
            BeaconModel p1 = (BeaconModel) o1;
            BeaconModel p2 = (BeaconModel) o2;
            if (p1.getDistance() < p2.getDistance())
                return -1;
            else if (p1.getDistance() == p2.getDistance())
                return 0;
            else if (p1.getDistance() > p2.getDistance())
                return 1;
            return 0;
        }
    };

    protected static double calculateAccuracy(int txPower, double rssi) {
        if (rssi == 0) {
            return -1.0; // if we cannot determine accuracy, return -1.
        }

        double ratio = rssi * 1.0 / txPower;
        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            return accuracy;
        }
    }

    private int FindMaxValue(List<Integer> list){
        int num = 0;
        maxKey = list.get(0).intValue();
        for(int k = 0;k<list.size();k++){
            if(maxKey==list.get(k).intValue()){
                num++;
            }
        }
        if(num > 8){
            maxKey = list.get(0);
        }else {
            maxKey=0;
        }
        return num;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        scanLeDevice(false);
    }
}
