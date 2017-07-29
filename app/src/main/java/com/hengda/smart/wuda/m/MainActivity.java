package com.hengda.smart.wuda.m;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.AutoNum;
import com.hengda.smart.wuda.m.bean.FloorBean;
import com.hengda.smart.wuda.m.bean.PlayerBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.AppConfig;
import com.hengda.smart.wuda.m.tools.BitmapProviderFile;
import com.hengda.smart.wuda.m.tools.GlideImgManager;
import com.hengda.smart.wuda.m.tools.auto.NumService;
import com.hengda.smart.wuda.m.ui.ac.SceneActivity;
import com.hengda.smart.wuda.m.view.dialog.DialogCenter;
import com.hengda.zwf.hddialog.DialogClickListener;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.qozix.tileview.TileView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasaActivity {
    TileView tileView;
    @BindView(R.id.fragment)
    FrameLayout fragment;

    double TILE_VIEW_WIDTH = 3721;
    double TILE_VIEW_HEIGHT = 2561;

    ImageView downSample;
    Bitmap bitmap;
    BitmapProviderFile bitmapProviderFile;

    int museum_id;

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;

    List<PlayerBean.DataBean.ExhibitInfoBean> datas = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    List<Integer> autoNums = new ArrayList<>();
    Intent intent;

    int preNum = 0;
    int autoNum = 0;
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
    @BindView(R.id.lin_bottom)
    LinearLayout linBottom;

    String saveName;
    MediaPlayer mediaPlayer;

    final static int TIME = 2;
    @BindView(R.id.top)
    RelativeLayout top;

    boolean isPlay = true;
    @BindView(R.id.ibtn_clode)
    ImageButton ibtnClode;
    @BindView(R.id.img_big)
    ImageView imgBig;
    @BindView(R.id.lin_pic)
    RelativeLayout linPic;
    @BindView(R.id.btn_camera)
    ImageButton btnCamera;

    private String current = "0";

    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;

    boolean is_recevied = true;

    String imgPath;

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        wakeLock.release();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                ivPlay.setImageResource(R.mipmap.play);
                isPlay = false;
            }
        }


    }

    @Subscribe
    public void getAuto(AutoNum autoNum) {
        Log.e("-----", autoNum.getAutoNum() + "----" + AppConfig.getDeviceNo());
        if (autoNums.size() > 0) {
            if (preNum != autoNum.getAutoNum()) {
                if (autoNums.contains(autoNum.getAutoNum())) {
                    Log.e("-----contain", autoNum.getAutoNum() + "");
                    if (is_recevied) {
                        if (autoNum.getAutoNum() == 11) {
                            preNum = autoNum.getAutoNum();
                            this.autoNum = preNum;
                            handler.sendEmptyMessage(1);
                            is_recevied = false;
                        } else {
                            preNum = autoNum.getAutoNum();
                            this.autoNum = preNum;
                            handler.sendEmptyMessage(1);
                        }
                    } else {
                        if (autoNum.getAutoNum() == 6) {
                            preNum = autoNum.getAutoNum();
                            this.autoNum = preNum;
                            handler.sendEmptyMessage(1);
                            is_recevied = true;
                        }
                    }
                } else {
                    upLoad(autoNum.getAutoNum());
                }
            }
        } else {

        }
    }

    private void upLoad(int num) {
        Log.e("====", num + "----" + AppConfig.getDeviceNo());
        RequestApi.getInstance().getFloor(new HttpRequestSubscriber<FloorBean.DataBean>() {
                                              @Override
                                              public void failed(Throwable e) {
                                                  super.failed(e);
                                              }

                                              @Override
                                              public void succeed(FloorBean.DataBean dataBean) {
                                                  super.succeed(dataBean);
                                                  if (dataBean.getIs_valid() == 0) {

                                                  } else {
                                                      if (current.equals(dataBean.getMuseum_id())) {

                                                      } else {
                                                          Log.e("====", dataBean.getMuseum_id());
                                                          current = dataBean.getMuseum_id();
                                                          DialogCenter.showDialog(MainActivity.this, new DialogClickListener() {
                                                              @Override
                                                              public void p() {
                                                                  DialogCenter.hideDialog();
                                                                  destroyTileview();

                                                                  museum_id = Integer.parseInt(dataBean.getMuseum_id());
                                                                  loadData();
                                                              }

                                                              @Override
                                                              public void n() {
                                                                  DialogCenter.hideDialog();
                                                              }
                                                          }, new String[]{"提示", "是否切换到" + dataBean.getMuseum_name() + "?", "确定", "取消"});
                                                      }

                                                  }
                                              }
                                          },
                String.format("%04d", num), AppConfig.getDeviceNo());

    }

    private void destroyTileview() {
        for (int i = 0; i < imageViewList.size(); i++) {
            tileView.removeMarker(imageViewList.get(i));
        }
        tileView.removeView(downSample);
        tileView.destroy();
        tileView = null;
        fragment.removeView(tileView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        intent = new Intent(MainActivity.this, NumService.class);
                        startService(intent);

                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Log.e("权限拒绝", "权限拒绝");
                    }
                });
        museum_id = getIntent().getExtras().getInt("Museum", 1);
        Log.e("--------", museum_id + "");
        bitmapProviderFile = new BitmapProviderFile();
        loadData();

    }

    void loadData() {
        preNum = 0;
        if (isConnection()) {
            showDialog();
            if (isWifi()) {
                RequestApi.getInstance().getPlayers(new HttpRequestSubscriber<PlayerBean.DataBean>() {
                    @Override
                    public void failed(Throwable e) {
                        super.failed(e);
                        Log.e("-----", e.getMessage().toString());
                        hideDialog();
                    }

                    @Override
                    public void succeed(PlayerBean.DataBean dataBean) {
                        super.succeed(dataBean);
                        hideDialog();
                        datas.clear();
                        datas.addAll(dataBean.getExhibit_info());
                        for(int i=0;i<datas.size();i++){
                            Log.e(i+"----------",datas.get(i).getBlue_teeth_id()+"----"+datas.get(i).getTitle());
                        }
                        TILE_VIEW_WIDTH = Double.parseDouble(dataBean.getMuseum_info().getWidth());
                        TILE_VIEW_HEIGHT = Double.parseDouble(dataBean.getMuseum_info().getHeight());
                        tvTop.setText(dataBean.getMuseum_info().getTitle());
                        initTileView(dataBean.getMuseum_info().getMap_addr());
                    }
                }, String.format("%04d", museum_id), Contants.Current_Lan, AppConfig.getDeviceNo());
            }
        }

    }

    void initTileView(String url) {
        imageViewList.clear();
        autoNums.clear();
        Log.e("-------", url + "/img.png");
        for (int i = 0; i < datas.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageViewList.add(imageView);
            autoNums.add(Integer.parseInt(datas.get(i).getBlue_teeth_id()));
        }
//        bitmap = bitmapProviderFile.getBitmap(url +"/img.png",MainActivity.this);
        tileView = new TileView(this);
        tileView.setTransitionsEnabled(false);
        tileView.setSize((int) TILE_VIEW_WIDTH, (int) TILE_VIEW_HEIGHT);
        tileView.defineBounds(0, 0, TILE_VIEW_WIDTH, TILE_VIEW_HEIGHT);
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        tileView.setScaleLimits(1.0f, 2.0f);
        tileView.post(new Runnable() {
            @Override
            public void run() {
                tileView.setScale(0.8f);
            }
        });
        addDetailLevel(url);
        adMark();
        fragment.addView(tileView);
    }

    /**
     * 根据展厅加载地图
     */
    private void addDetailLevel(String url) {
        /**
         * 添加各缩放级别的地图
         //         *
         */
        tileView.setBitmapProvider(new BitmapProviderFile());
        tileView.addDetailLevel(1.000f, url + "/1_1000_%d_%d.png");
        tileView.addDetailLevel(0.500f, url + "/1_500_%d_%d.png");
        tileView.addDetailLevel(0.250f, url + "/1_250_%d_%d.png");
        tileView.addDetailLevel(0.125f, url + "/1_125_%d_%d.png");
        /**
         * 添加底图
         */
        downSample = new ImageView(this);
        Glide.with(this)
                .load(url + "/img.png")
                .into(downSample);
        tileView.addView(downSample, 0);
    }

    private void adMark() {
        for (int i = 0; i < datas.size(); i++) {
            Glide.with(this)
                    .load(datas.get(i).getMap_img_path())
                    .placeholder(R.mipmap.default_mark_m)
                    .override(99, 119)
                    .into(imageViewList.get(i));
            imageViewList.get(i).setTag(datas.get(i));
            tileView.addMarker(imageViewList.get(i), Double.parseDouble(datas.get(i).getAxis_x()), Double.parseDouble(datas.get(i).getAxis_y()),
                    null, null);
            final int position = i;
            imageViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (datas.get(position).getType().equals("exhibit")) {
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
                        saveName = datas.get(position).getId();
                        imgPath = datas.get(position).getImg_url();
                        Glide.with(MainActivity.this).load(imgPath).placeholder(R.mipmap.map_detault).into(imgBig);
                        String url = datas.get(position).getMp3_path();
                        downLoad(url, datas.get(position).getRoute_img_path(), datas.get(position).getTitle());
                    } else {
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
                        linBottom.setVisibility(View.INVISIBLE);
                        Bundle bundle = new Bundle();
                        bundle.putString("exhibt_id", datas.get(position).getId());
                        openActivity(SceneActivity.class, bundle);

                    }
                }
            });
        }
    }

    private void replaceMark(int autoNum) {
        int myposition = -1;
        for (int i = 0; i < imageViewList.size(); i++) {
            tileView.removeMarker(imageViewList.get(i));
        }
        imageViewList.clear();

        for (int i = 0; i < datas.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageViewList.add(imageView);
        }

        for (int i = 0; i < imageViewList.size(); i++) {
            if (autoNum == Integer.parseInt(datas.get(i).getBlue_teeth_id())) {
                if (myposition == -1) {
                    myposition = i;
                }
                Glide.with(this)
                        .load(datas.get(i).getMap_img_path())
                        .placeholder(R.mipmap.default_mark_m)
                        .override(148, 178)
                        .into(imageViewList.get(i));
            } else {
                Glide.with(this)
                        .load(datas.get(i).getMap_img_path())
                        .placeholder(R.mipmap.default_mark_m)
                        .override(99, 119)
                        .into(imageViewList.get(i));
            }

            imageViewList.get(i).setTag(datas.get(i));
            tileView.addMarker(imageViewList.get(i), Double.parseDouble(datas.get(i).getAxis_x()), Double.parseDouble(datas.get(i).getAxis_y()),
                    null, null);
            final int position = i;
            imageViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (datas.get(position).getType().equals("exhibit")) {
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

                        saveName = datas.get(position).getId();
                        imgPath = datas.get(position).getImg_url();
                        Glide.with(MainActivity.this).load(imgPath).placeholder(R.mipmap.map_detault).into(imgBig);
                        String url = datas.get(position).getMp3_path();
                        downLoad(url, datas.get(position).getRoute_img_path(), datas.get(position).getTitle());
                    } else {
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
                        linBottom.setVisibility(View.INVISIBLE);
                        Bundle bundle = new Bundle();
                        bundle.putString("exhibt_id", datas.get(position).getId());
                        openActivity(SceneActivity.class, bundle);
                    }
                }
            });
        }

        frameTo(Double.parseDouble(datas.get(myposition).getAxis_x()), Double.parseDouble(datas.get(myposition).getAxis_y()));

    }

    public void frameTo(final double x, final double y) {
        try {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        tileView.slideToAndCenter(x, y);
                        tileView.setAnimationDuration(2000);
                    } catch (Exception e) {

                    }

                }
            });

        } catch (Exception e) {

        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    replaceMark(autoNum);
                    break;
                case TIME:
                    try {
                        int current = mediaPlayer.getCurrentPosition();
                        int i = current;
                        playCurrenttime(i);
                        handler.sendEmptyMessageDelayed(TIME, 500);
                    } catch (Exception e) {

                    }
                    break;
            }

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
        mediaPlayer = null;
    }

    void downLoad(String url, String imgUrl, String name) {
        if (isConnection()) {
            linBottom.setVisibility(View.VISIBLE);
            linPic.setVisibility(View.VISIBLE);
            GlideImgManager.glideLoader(this, imgUrl, R.mipmap.ic_launcher, R.mipmap.ic_launcher, ivIcon);
            linBottom.setVisibility(View.VISIBLE);
            tvName.setText(name);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(url);//设置播放的数据源。
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
                        linBottom.setVisibility(View.INVISIBLE);
                        linPic.setVisibility(View.GONE);

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
        }

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    if (isConnection()) {
                        mediaPlayer.seekTo(i);
                    } else {
                        Toast.makeText(MainActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
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

    void playCurrenttime(int time) {
        seekbar.setProgress(time);
        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        tvCurrent.setText(String.format("%02d:%02d", minute,
                second));
    }

    @OnClick({R.id.top_back, R.id.iv_play, R.id.iv_icon, R.id.ibtn_clode,R.id.btn_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
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
                linPic.setVisibility(View.VISIBLE);
                break;
            case R.id.ibtn_clode:
                linPic.setVisibility(View.GONE);
                break;
            case R.id.btn_camera:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 启动相机
                startActivity(intent1);
                break;
        }
    }
}
