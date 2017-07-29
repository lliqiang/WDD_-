package com.hengda.smart.wuda.m.ui.ac;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.SceneBean;
import com.hengda.smart.wuda.m.http.HttpRequestSubscriber;
import com.hengda.smart.wuda.m.http.RequestApi;
import com.hengda.smart.wuda.m.tools.BitmapProviderFile;
import com.hengda.smart.wuda.m.tools.GlideImgManager;
import com.qozix.tileview.TileView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/4/15.
 */

public class SceneActivity extends BasaActivity {
    String scene_id;


    TileView tileView;

    double TILE_VIEW_WIDTH = 3721;
    double TILE_VIEW_HEIGHT = 2561;

    List<SceneBean.DataBean.ExhibitInfoBean> datas = new ArrayList<>();
    List<ImageView> imageViewList = new ArrayList<>();
    Bitmap bitmap;
    BitmapProviderFile bitmapProviderFile;
    ImageView downSample;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.top)
    RelativeLayout top;
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

    MediaPlayer mediaPlayer;
    final static int TIME = 2;
    boolean isPlay = false;

    private PowerManager powerManager = null;
    private PowerManager.WakeLock wakeLock = null;

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        ButterKnife.bind(this);
        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        //是否需计算锁的数量
        wakeLock.setReferenceCounted(false);
//        setToolbar();
        init();
        bitmapProviderFile = new BitmapProviderFile();
        loadData();

    }

    void init() {
        scene_id = getIntent().getStringExtra("exhibt_id");
        Log.e("------", "-----" + scene_id + "-----");
    }

    void loadData() {
        if (isConnection()) {
            if (isWifi()) {
                RequestApi.getInstance().
                        getSceneDetails(new HttpRequestSubscriber<SceneBean.DataBean>() {
                                            @Override
                                            public void failed(Throwable e) {
                                                super.failed(e);
                                            }

                                            @Override
                                            public void succeed(SceneBean.DataBean dataBean) {
                                                super.succeed(dataBean);
                                                datas.clear();
                                                datas.addAll(dataBean.getExhibit_info());

                                                TILE_VIEW_WIDTH = Double.parseDouble(dataBean.getScene_info().getWidth());
                                                TILE_VIEW_HEIGHT = Double.parseDouble(dataBean.getScene_info().getHeight());
                                                Log.e("------", TILE_VIEW_WIDTH+"========"+TILE_VIEW_HEIGHT);
                                                initTileView(dataBean.getScene_info().getImg());
                                            }
                                        }
                                , scene_id, Contants.Current_Lan);
            }
        }
    }

    void initTileView(String url) {
        Log.e("-------", url);
        for (int i = 0; i < datas.size(); i++) {
            ImageView imageView = new ImageView(SceneActivity.this);
            imageViewList.add(imageView);
        }

//        bitmap = bitmapProviderFile.getBitmap(url + "/img.png", this);
        tileView = new TileView(this);
        tileView.setTransitionsEnabled(false);
        tileView.setSize((int) TILE_VIEW_WIDTH, (int) TILE_VIEW_HEIGHT);
        tileView.defineBounds(0, 0, TILE_VIEW_WIDTH, TILE_VIEW_HEIGHT);
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        tileView.setScaleLimits(0, 2.0f);
        addDetailLevel(url);
        tileView.post(new Runnable() {
            @Override
            public void run() {
                tileView.setScale(1.0f);
            }
        });
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
        /**
         * 添加底图
         */
        downSample = new ImageView(this);
        Glide.with(this)
                .load(url )
                .into(downSample);
        tileView.addView(downSample, 0);
    }

    private void adMark() {
        for (int i = 0; i < datas.size(); i++) {
//            Glide.with(this)
//                    .load(datas.get(i).getMap_img_path())
//                    .placeholder(R.mipmap.default_mark_m)
//                    .override(100, 100)
//                    .into(imageViewList.get(i));


            imageViewList.get(i).setImageResource(R.drawable.speak_spread);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageViewList.get(i).getDrawable();
            animationDrawable.start();
            imageViewList.get(i).setTag(datas.get(i));
            tileView.addMarker(imageViewList.get(i), Double.parseDouble(datas.get(i).getAxis_x()), Double.parseDouble(datas.get(i).getAxis_y()),
                    null, null);
            final int position = i;
            imageViewList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                        linBottom.setVisibility(View.VISIBLE);
                        String url = datas.get(position).getMp3_path();
                        downLoad(url, datas.get(position).getImg(), datas.get(position).getTitle());
                }
            });
        }
    }


    void downLoad(String url, String imgUrl, String name) {
        if (isConnection()) {
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

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show();
            }
        }

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);
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


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
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

    @OnClick({R.id.top_back, R.id.iv_play})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.iv_play:
                if(isPlay){
                    mediaPlayer.pause();
                }else {
                    mediaPlayer.start();
                }
                isPlay = !isPlay;
                ivPlay.setImageResource(isPlay?R.mipmap.pause:R.mipmap.play);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
        mediaPlayer = null;
    }
}
