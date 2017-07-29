package com.hengda.smart.wuda.m.ui.ac;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.zhouwei.library.CustomPopWindow;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.HD_APP_Config;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.ViewBean;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.ToastUtils;
import com.hengda.zwf.commonutil.FileUtils;
import com.hengda.zwf.commonutil.StatusBarCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zlc.season.rxdownload.RxDownload;
import zlc.season.rxdownload.entity.DownloadStatus;

public class FreeTourActivity extends BasaActivity implements View.OnClickListener, SensorEventListener {

    @BindView(R.id.mw_baidu)
    MapView mMapView;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.iv_autoPlay)
    ImageView ivAutoPlay;
    @BindView(R.id.iv_route_baidu)
    ImageView ivRouteBaidu;
    @BindView(R.id.iv_camera_baidu)
    ImageView ivCameraBaidu;
    @BindView(R.id.iv_add_scale)
    ImageView ivAddScale;
    @BindView(R.id.iv_remove_scale)
    ImageView ivRemoveScale;
    @BindView(R.id.iv_loc_baidu)
    ImageView ivLocBaidu;
    @BindView(R.id.ll_bottom_route)
    RelativeLayout llBottomRoute;
    @BindView(R.id.btn_close_bottom)
    Button btnCloseBottom;
    @BindView(R.id.tv_bottom_route)
    TextView tvBottomRoute;
    @BindView(R.id.tv_auto_map)
    TextView tvAutoMap;
    @BindView(R.id.tv_route_map)
    TextView tvRouteMap;
    @BindView(R.id.ibtn_close_BaiDuMap)
    ImageButton ibtnCloseBaiDuMap;
    @BindView(R.id.iv_Play_BaiDuMap)
    ImageView ivPlayBaiDuMap;
    @BindView(R.id.rl_play_baiduMap)
    RelativeLayout rlPlayBaiduMap;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_current)
    TextView tvCurrent;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.ll_play_baidumap)
    LinearLayout llPlayBaidumap;
    @BindView(R.id.activity_free_tour)
    RelativeLayout activityFreeTour;
    @BindView(R.id.rl_loc_and_camera)
    RelativeLayout rlLocAndCamera;
    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    public BDLocationListener myListener;
    private double mCurrentLat;
    private double mCurrentLon;
    float zoomLevel;
    final static int TIME = 2;
    private float mCurrentX;
    private boolean isFirstLocation = true;
    private CustomPopWindow mCustomPopWindow;
    private TextView tv_routeOne;
    private TextView tv_routeTwo;
    private TextView tv_routeThree;
    private View view;
    private SensorManager mSensorManager;
    private double lastX = 0.0;
    private int mCurrentDirection = 0;
    private MyLocationData locData;
    private float mCurrentAccracy;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private BitmapDescriptor mCurrentMarker;
    private List<ViewBean.DataBean.FeatureInfoBean> infoBeanList;
    private List<Marker> markerList;
    private Marker mMarkerA;
    private Marker mMarkerB;
    MediaPlayer mediaPlayer;
    private ViewBean mViewBean;
    private boolean isPlay = true;
    private String lastFeature_id;
    private Map<String, Double> doubleMap;
    private ViewBean.DataBean.FeatureInfoBean lastFeatureInfoBean;
    List<LatLng> points;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME:
                    try {
                        int current = mediaPlayer.getCurrentPosition();
                        int i = current;
                        playCurrentTime(i);
                        handler.sendEmptyMessageDelayed(TIME, 500);
                    } catch (Exception e) {

                    }
                    break;
            }
        }
    };

    private Polyline mPolyline;
    private OverlayOptions ooPolyline;
    private ValueAnimator animator;
    private double lastDistance = 1000;
    private double tempLastDistance = 1000;
    ViewBean.DataBean.FeatureInfoBean tempInfoBean;
    private int tempNum = 0;
    private int mlastX;
    private int mlastY;
    private List<Double> mathList;
    private int distanceX;
    private int distanceY;
    private boolean flag;
    private double distance;
    private Subscription subscription;

    private Marker curMarkerActive;
    private Marker tempM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.activity_free_tour, null);
        setContentView(view);
        mathList = new ArrayList<>();
        doubleMap = new HashMap<>();
        animator = ValueAnimator.ofFloat(-380);
        animator.setDuration(500);
        animator.start();
        StatusBarCompat.translucentStatusBar(this);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        ButterKnife.bind(this);
        setAutoState();
        initTypeFace();
        initLocation();
        initListener();
        points = new ArrayList<>();
        infoBeanList = new ArrayList<>();
        markerList = new ArrayList<>();
        RequestApi.getInstance().getViewInfo(new Subscriber<ViewBean>() {
            @Override
            public void onCompleted() {
                for (ViewBean.DataBean.FeatureInfoBean f : infoBeanList) {
                    showNormalMarker(f);
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ViewBean viewBean) {
                infoBeanList.clear();
                mViewBean = viewBean;
                infoBeanList.addAll(viewBean.getData().getFeature_info());
            }
        }, "0005", AppConfig.getLanguage(), AppConfig.getDeviceNo());

        mBaiduMap.setOnMarkerClickListener(marker -> {
                    if (marker != curMarkerActive) {
                        llPlayBaidumap.setVisibility(View.VISIBLE);
                        rlPlayBaiduMap.setVisibility(View.VISIBLE);
                        rlLocAndCamera.setVisibility(View.GONE);
                        if (curMarkerActive != null) {
                            //TODO activeMarker to normalMaker
                            Bundle extraInfo = curMarkerActive.getExtraInfo();
                            curMarkerActive.remove();
                            showClickNormalMarker((ViewBean.DataBean.FeatureInfoBean) extraInfo.getSerializable("FeatureInfoBean"));
                            showActiveMarker(marker, true);
                        } else {
                            //TODO clickMarker to activeMarker
                            showActiveMarker(marker, true);
                        }
                        releaseMediaPlayer();

                        ViewBean.DataBean.FeatureInfoBean featureInfoBean1 = (ViewBean.DataBean.FeatureInfoBean) marker.getExtraInfo().getSerializable("FeatureInfoBean");
                        initPlay(featureInfoBean1);
                        loadMarkRes(featureInfoBean1);
                    } else {
                        llPlayBaidumap.setVisibility(View.VISIBLE);
                        rlPlayBaiduMap.setVisibility(View.VISIBLE);
                        rlLocAndCamera.setVisibility(View.GONE);
                        ViewBean.DataBean.FeatureInfoBean featureInfoBean1 = (ViewBean.DataBean.FeatureInfoBean) marker.getExtraInfo().getSerializable("FeatureInfoBean");
                        initPlay(featureInfoBean1);
                        Glide.with(FreeTourActivity.this).load(featureInfoBean1.getImg()).placeholder(R.mipmap.map_detault).into(ivPlayBaiDuMap);
                        Glide.with(FreeTourActivity.this).load(featureInfoBean1.getList_img()).into(ivIcon);
                    }
                    ivPlay.setImageResource(R.mipmap.pause);
                    return false;


                }


        );


        clickToDispear();
    }

    private void clickToDispear() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mediaPlayer != null && llBottomRoute.getVisibility() != View.VISIBLE) {
                    if (rlPlayBaiduMap.getVisibility() != View.VISIBLE) {
                        animator.reverse();
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                rlLocAndCamera.setVisibility(View.VISIBLE);
                                rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                            }
                        });
                    }

                }
                releaseMediaPlayer();
                rlLocAndCamera.setVisibility(View.VISIBLE);
                if (llBottomRoute.getVisibility() == View.VISIBLE && rlPlayBaiduMap.getVisibility() == View.VISIBLE) {
                    animator.start();
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            rlLocAndCamera.setVisibility(View.VISIBLE);
                            rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                        }
                    });
                }
                llPlayBaidumap.setVisibility(View.GONE);
                rlPlayBaiduMap.setVisibility(View.GONE);

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private void loadMarkRes(ViewBean.DataBean.FeatureInfoBean featureInfoBean1) {
        if (!FileUtils.isFileExist(AppConfig.getVoicePath(featureInfoBean1.getFeature_id())) && isConnection()) {
            subscription = RxDownload.getInstance().download(featureInfoBean1.getMp3_path(), featureInfoBean1.getFeature_id() + ".mp3", AppConfig.getDefaultFileDir() + "/cache")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<DownloadStatus>() {
                        @Override
                        public void call(DownloadStatus downloadStatus) {
                            if (downloadStatus.getTotalSize() == downloadStatus.getDownloadSize()) {

                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });

        }
    }

    private void showNormalMarker(final ViewBean.DataBean.FeatureInfoBean f) {
        Glide.with(FreeTourActivity.this)
                .load(f.getPoi_img())
                .asBitmap()
                .override(120, 200)
                .fitCenter()
                .placeholder(R.mipmap.img_mark_defaut)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        BitmapDescriptor bdA = BitmapDescriptorFactory.fromBitmap(resource);
                        LatLng llA = new LatLng(Double.parseDouble(f.getJingdu()), Double.parseDouble(f.getWeidu()));
                        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(100).draggable(false);
                        ooA.animateType(MarkerOptions.MarkerAnimateType.grow).period(1);
                        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("FeatureInfoBean", f);
                        mMarkerA.setExtraInfo(bundle);
                        mMarkerA.setFlat(false);
                        markerList.add(mMarkerA);
                    }
                });
    }


    private void showClickNormalMarker(final ViewBean.DataBean.FeatureInfoBean f) {
        Glide.with(FreeTourActivity.this)
                .load(f.getPoi_img())
                .asBitmap()
                .override(120, 200)
                .fitCenter()
                .placeholder(R.mipmap.img_mark_defaut)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        BitmapDescriptor bdA = BitmapDescriptorFactory.fromBitmap(resource);
                        LatLng llA = new LatLng(Double.parseDouble(f.getJingdu()), Double.parseDouble(f.getWeidu()));
                        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(100).draggable(false);
                        ooA.animateType(MarkerOptions.MarkerAnimateType.grow).period(1);
                        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("FeatureInfoBean", f);
                        mMarkerA.setExtraInfo(bundle);
                        mMarkerA.setFlat(false);
                    }
                });


    }


    private void showActiveMarker(Marker marker, boolean flag) {
        marker.remove();
        ViewBean.DataBean.FeatureInfoBean featureInfoBean = (ViewBean.DataBean.FeatureInfoBean) marker.getExtraInfo().getSerializable("FeatureInfoBean");
        Glide.with(FreeTourActivity.this)
                .load(featureInfoBean.getBig_poi_img())
                .asBitmap().override(200, 280).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.img_mark_defaut)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        BitmapDescriptor bdA;
                        if (resource == null) {
                            bdA = BitmapDescriptorFactory.fromResource(R.mipmap.img_mark_defaut);
                        } else {
                            bdA = BitmapDescriptorFactory.fromBitmap(resource);
                        }
                        LatLng llA = new LatLng(Double.parseDouble(featureInfoBean.getJingdu()), Double.parseDouble(featureInfoBean.getWeidu()));
                        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(100).draggable(false);
                        ooA.animateType(MarkerOptions.MarkerAnimateType.grow).period(1);
                        curMarkerActive = (Marker) (mBaiduMap.addOverlay(ooA));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("FeatureInfoBean", featureInfoBean);
                        curMarkerActive.setExtraInfo(bundle);
                    }
                });
        if (flag) {

            llPlayBaidumap.setVisibility(View.VISIBLE);
            rlPlayBaiduMap.setVisibility(View.VISIBLE);
            rlLocAndCamera.setVisibility(View.GONE);
            Glide.with(FreeTourActivity.this).load(featureInfoBean.getImg()).placeholder(R.mipmap.map_detault).into(ivPlayBaiDuMap);
            Glide.with(FreeTourActivity.this).load(featureInfoBean.getList_img()).into(ivIcon);
        }

    }


    private void setAutoState() {
        ivAutoPlay.setImageResource(HD_APP_Config.getAutoPlay() ? R.mipmap.img_auto_on : R.mipmap.img_auto_off);
        /*if (HD_APP_Config.getAutoPlay()) {
            ivAutoPlay.setImageResource(R.mipmap.img_auto_on);
        } else {
            ivAutoPlay.setImageResource(R.mipmap.img_auto_off);
        }*/
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } else {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    private void initPlay(ViewBean.DataBean.FeatureInfoBean featureInfoBean) {
        releaseMediaPlayer();
        tvName.setTypeface(HD_Application.typeface);
        tvName.setText(featureInfoBean.getTitle());
        try {
            mediaPlayer = new MediaPlayer();
            if (FileUtils.isFileExist(AppConfig.getVoicePath(featureInfoBean.getFeature_id()))) {
                mediaPlayer.setDataSource(AppConfig.getVoicePath(featureInfoBean.getFeature_id()));
            } else {
                mediaPlayer.setDataSource(featureInfoBean.getMp3_path());//设置播放的数据源。
            }
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();//异步的准备
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int i = mp.getDuration();
                    totalTime(i);
                    isPlay = true;
                    mediaPlayer.start();
                    handler.sendEmptyMessage(TIME);
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer();
                    llPlayBaidumap.setVisibility(View.GONE);
                    rlPlayBaiduMap.setVisibility(View.GONE);
                    if (llBottomRoute.getVisibility() != View.VISIBLE) {
                        animator.reverse();
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                rlLocAndCamera.setVisibility(View.VISIBLE);
                                rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTypeFace() {
        tvTop.setText(R.string.freetour_free_walk);
        tvTop.setTypeface(HD_Application.typeface);
        llBottomRoute.setVisibility(View.GONE);
        btnCloseBottom.setTypeface(HD_Application.typeface);
        tvBottomRoute.setTypeface(HD_Application.typeface);
        tvAutoMap.setTypeface(HD_Application.typeface);
        tvRouteMap.setTypeface(HD_Application.typeface);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    }

    private void showPop() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
        LinearLayout container = ((LinearLayout) contentView.findViewById(R.id.ll_pop_container));
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setOutsideTouchable(true)
                .create()
                .showAtLocation(view, Gravity.RIGHT, 240, -350);
        initRouteView(container);
    }

    private void initRouteView(LinearLayout container) {
        for (int i = 0; i < mViewBean.getData().getRecommand_lines().size(); i++) {
            TextView tv = new TextView(FreeTourActivity.this);
            tv.setCompoundDrawables(null, null, null, getResources().getDrawable(R.mipmap.img_line_route));
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setTextSize(18);
            tv.setPadding(20, 20, 20, 20);
            tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            tv.setTypeface(HD_Application.typeface);
            tv.setText(mViewBean.getData().getRecommand_lines().get(i).getTitle());
            container.addView(tv);
            int finalI = i;
            makeLine(container, i, finalI);
        }
    }

    private void makeLine(LinearLayout container, int i, final int finalI) {
        container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rlPlayBaiduMap.getVisibility() == View.GONE) {
                    animator.start();
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            rlLocAndCamera.setVisibility(View.VISIBLE);
                            rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                        }
                    });
                }
                if (mPolyline != null) {
                    mPolyline.remove();
                }
                points.clear();
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                for (int j = 0; j < mViewBean.getData().getRecommand_lines().get(finalI).getPosition_info().size(); j++) {
                    LatLng p1 = new LatLng(Double.parseDouble(mViewBean.getData().getRecommand_lines().get(finalI).getPosition_info().get(j).getJingdu()), Double.parseDouble(mViewBean.getData().getRecommand_lines().get(finalI).getPosition_info().get(j).getWeidu()));
                    points.add(p1);
                }
                ooPolyline = new PolylineOptions().width(10)
                        .color(0xAAFF7F01).points(points);//ff7f01
                mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
                llBottomRoute.setVisibility(View.VISIBLE);
                tvBottomRoute.setText(mViewBean.getData().getRecommand_lines().get(finalI).getTitle());
            }
        });
    }

    private void initLocation() {
        mBaiduMap = mMapView.getMap();
        zoomLevel = mBaiduMap.getMapStatus().zoom;
        myListener = new MyLocationListener();
        mLocClient = new LocationClient(getApplicationContext());
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mMapView.showZoomControls(false);
// 定位初始化
        mLocClient.registerLocationListener(myListener);
        // 删除百度地图LoGo
        mMapView.removeViewAt(1);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, null));
        LatLng ll = new LatLng(100, 100);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        builder.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(FreeTourActivity.this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                ivPlay.setImageResource(R.mipmap.play);
                isPlay = false;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        releaseMediaPlayer();
    }

    private void initListener() {
        topBack.setOnClickListener(this);
        ivAutoPlay.setOnClickListener(this);
        ivRouteBaidu.setOnClickListener(this);
        ivAddScale.setOnClickListener(this);
        ivRemoveScale.setOnClickListener(this);
        ivCameraBaidu.setOnClickListener(this);
        ivLocBaidu.setOnClickListener(this);
        btnCloseBottom.setOnClickListener(this);
        ibtnCloseBaiDuMap.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivIcon.setOnClickListener(this);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    if (i >= 0 && mediaPlayer != null) {
                        mediaPlayer.seekTo(i);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                HD_Application.clearActivity();
                finish();
                break;
            case R.id.iv_autoPlay:
                if (HD_APP_Config.getAutoPlay()) {
                    HD_APP_Config.setAutoPlay(false);
                    ivAutoPlay.setImageResource(R.mipmap.img_auto_off);
                } else {
                    HD_APP_Config.setAutoPlay(true);
                    ivAutoPlay.setImageResource(R.mipmap.img_auto_on);
                    lastFeature_id = "replay";
                }
                break;
            case R.id.iv_route_baidu:
                showPop();
                break;
            case R.id.iv_add_scale:
                zoomLevel = mBaiduMap.getMapStatus().zoom;
                if (zoomLevel <= 21) {
                    MapStatusUpdateFactory.zoomIn();
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                    ivAddScale.setEnabled(true);
                } else {
                    ivRemoveScale.setEnabled(true);
                }
                break;
            case R.id.iv_remove_scale:
                if (zoomLevel > 4) {
                    mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                    ivRemoveScale.setEnabled(true);
                } else {
                    ivAddScale.setEnabled(false);
                }
                break;
            case R.id.iv_camera_baidu:
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.iv_loc_baidu:
                isFirstLocation = true;
                if (mLocClient != null && mLocClient.isStarted()) {
                    ToastUtils.showToast(HD_Application.context, "正在定位...");
                    mLocClient.requestLocation();
                }
                break;
            case R.id.btn_close_bottom:
                llBottomRoute.setVisibility(View.GONE);
                mPolyline.remove();
                if (mediaPlayer == null) {
                    animator.reverse();
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            rlLocAndCamera.setVisibility(View.VISIBLE);
                            rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                        }
                    });
                }
                break;
            case R.id.ibtn_close_BaiDuMap:
                animator.start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        rlLocAndCamera.setVisibility(View.VISIBLE);
                        rlLocAndCamera.setTranslationY((float) animation.getAnimatedValue());
                        rlPlayBaiduMap.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.iv_play:
                if (isPlay) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                isPlay = !isPlay;
                ivPlay.setImageResource(isPlay ? R.mipmap.pause : R.mipmap.play);
                break;
            case R.id.iv_icon:
                rlPlayBaiduMap.setVisibility(View.VISIBLE);
                rlLocAndCamera.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            location.setRadius(50f);
            tempLastDistance = 600;
            for (int i = 0; i < markerList.size(); i++) {
                ViewBean.DataBean.FeatureInfoBean featureInfoBean = (ViewBean.DataBean.FeatureInfoBean) markerList.get(i).getExtraInfo().getSerializable("FeatureInfoBean");
                LatLng myLL = new LatLng(location.getLatitude(), location.getLongitude());
                LatLng toLL = new LatLng(Double.parseDouble(featureInfoBean.getJingdu()), Double.parseDouble(featureInfoBean.getWeidu()));
                distance = DistanceUtil.getDistance(myLL, toLL);
                if (tempLastDistance > distance) {
                    tempLastDistance = distance;
                    tempInfoBean = featureInfoBean;
                    tempNum = i;
                }
            }
            HD_APP_Config.setTempNum(lastFeature_id);
            if (tempInfoBean != null) {
                if (tempLastDistance < 500 && isReplay(tempInfoBean.getFeature_id())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (HD_APP_Config.getAutoPlay()) {
                                initPlay(tempInfoBean);
                                if (ivPlay != null) {
                                    ivPlay.setImageResource(R.mipmap.pause);
                                }
                            }


                            if (curMarkerActive != null) {
                                curMarkerActive.remove();
                                if (!((ViewBean.DataBean.FeatureInfoBean)curMarkerActive.getExtraInfo().getSerializable("FeatureInfoBean")).getFeature_id().equals(tempInfoBean.getFeature_id())){
                                    showClickNormalMarker((ViewBean.DataBean.FeatureInfoBean) curMarkerActive.getExtraInfo().getSerializable("FeatureInfoBean"));
                                }

                            }
//
                                if (mMarkerA != null&&((ViewBean.DataBean.FeatureInfoBean)mMarkerA.getExtraInfo().getSerializable("FeatureInfoBean")).getFeature_id().equals(tempInfoBean.getFeature_id())) {
                                    mMarkerA.remove();
                                }
//

                            showActiveMarker(markerList.get(tempNum), HD_APP_Config.getAutoPlay());

                        }
                    });
                }
            }

            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
            mBaiduMap.setMyLocationConfigeration(configuration);
            if (isFirstLocation) {
                //获取经纬度
                isFirstLocation = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    void totalTime(int totalTime) {
        seekbar.setMax(totalTime);
        totalTime /= 1000;
        int minute = totalTime / 60;
        int second = totalTime % 60;
        minute %= 60;
        tvTotal.setText(String.format("%02d:%02d", minute,
                second));
        tvCurrent.setText("00:00");
    }

    void playCurrentTime(int time) {
        seekbar.setProgress(time);
        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        tvCurrent.setText(String.format("%02d:%02d", minute, second));
    }

    private boolean isReplay(String feature_id) {
        boolean temp_flag = false;
        if (!TextUtils.isEmpty(feature_id) && !TextUtils.equals(feature_id, lastFeature_id)) {
            lastFeature_id = feature_id;
            temp_flag = true;
        }
        return temp_flag;
    }

}
