package com.hengda.smart.wuda.m.ui.ac;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.bean.TicketBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketDetailActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.iv_ticket_detial)
    ImageView ivTicketDetial;
    @BindView(R.id.tv_title_ticket_detail)
    TextView tvTitleTicketDetail;
    @BindView(R.id.tv_textState)
    TextView tvTextState;
    @BindView(R.id.tv_money_discount_ticket_detail)
    TextView tvMoneyDiscountTicketDetail;
    @BindView(R.id.tv_money_ticket_detail)
    TextView tvMoneyTicketDetail;
    @BindView(R.id.tv_buy_time_ticketDetail)
    TextView tvBuyTimeTicketDetail;
    @BindView(R.id.tv_lost_time_ticketDetail)
    TextView tvLostTimeTicketDetail;
    @BindView(R.id.btn_ticket_detail)
    Button btnTicketDetail;
    @BindView(R.id.iv_scode_ticket_detail)
    ImageView ivScodeTicketDetail;
    private TicketBean.DataBean dataBean;
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        ButterKnife.bind(this);

        setType(tvTop, tvMoneyDiscountTicketDetail, tvMoneyTicketDetail, tvTextState, tvTitleTicketDetail, tvBuyTimeTicketDetail, tvLostTimeTicketDetail);
        setType(btnTicketDetail);
        topBack.setOnClickListener(v -> finish());
        tvTop.setText("门票");
        tvMoneyDiscountTicketDetail.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        dataBean = (TicketBean.DataBean) getIntent().getSerializableExtra("MyTicket");

        tvTitleTicketDetail.setText(dataBean.getTitle());
        tvBuyTimeTicketDetail.setText("购票时间："+dataBean.getCreate_time());
        tvLostTimeTicketDetail.setText("失效时间："+dataBean.getExpire_time());
        if (dataBean.getOri_price() != null) {
            tvMoneyTicketDetail.setText(dataBean.getOri_price().toString());
        }
        tvMoneyDiscountTicketDetail.setText(dataBean.getAfter_discount_price());





        Glide.with(this).load(dataBean.getTiaoxingma_img()).placeholder(R.mipmap.map_detault).into(ivScodeTicketDetail);
        Glide.with(this).load(dataBean.getTicket_img()).placeholder(R.mipmap.img_defaut_ticket).into(ivTicketDetial);

        temp = Integer.parseInt(dataBean.getStatus());

        switch (temp) {
            case 1:
                tvTextState.setText("未检票");
                btnTicketDetail.setVisibility(View.GONE);
                break;

            case 2:
                tvTextState.setText("已检票");
                btnTicketDetail.setVisibility(View.VISIBLE);
                break;

            case 3:
                tvTextState.setText("已收听讲解");
                btnTicketDetail.setVisibility(View.VISIBLE);
                break;
            case 4:
                tvTextState.setText("已失效");
                btnTicketDetail.setVisibility(View.GONE);
                ivScodeTicketDetail.setBackgroundResource(R.mipmap.bg_scode_no_effect);
                tvTitleTicketDetail.setTextColor(getResources().getColor(R.color.edit_active));
                tvBuyTimeTicketDetail.setTextColor(getResources().getColor(R.color.edit_active));
                tvLostTimeTicketDetail.setTextColor(getResources().getColor(R.color.edit_active));
                tvMoneyTicketDetail.setTextColor(getResources().getColor(R.color.edit_active));
                tvMoneyDiscountTicketDetail.setTextColor(getResources().getColor(R.color.edit_active));
                break;
        }


        btnTicketDetail.setOnClickListener(v -> {
            Contants.ACTIVITY_ID = 3;
            openActivity(MainActivity.class);
        });
    }

}
