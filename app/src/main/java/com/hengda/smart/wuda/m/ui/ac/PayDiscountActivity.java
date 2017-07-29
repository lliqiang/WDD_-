package com.hengda.smart.wuda.m.ui.ac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hengda.smart.wuda.m.R;
import com.hengda.smart.wuda.m.adapter.DiscountAdapter;
import com.hengda.smart.wuda.m.base.BasaActivity;
import com.hengda.smart.wuda.m.base.Contants;
import com.hengda.smart.wuda.m.base.HD_Application;
import com.hengda.smart.wuda.m.bean.PayBean;
import com.hengda.smart.wuda.m.tools.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayDiscountActivity extends BasaActivity {

    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_tip_no_discount)
    TextView tvTipNoDiscount;
    @BindView(R.id.ll_no_discount)
    LinearLayout llNoDiscount;
    @BindView(R.id.rw_discount_pay)
    RecyclerView rwDiscountPay;
    @BindView(R.id.btn_use_discount)
    Button btnUseDiscount;
    private PayBean mPayBean;
    private DiscountAdapter adapter;
    private Intent mIntent;
private PayBean.DataBean.YouhuiquanInfoBean youhuiquanInfoBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_discount);
        ButterKnife.bind(this);
        mIntent = new Intent();
        topBack.setOnClickListener(v -> finish());
        tvTop.setText("选择优惠券");
        setType(tvTop);
        setType(btnUseDiscount);
        rwDiscountPay.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        mPayBean = (PayBean) getIntent().getSerializableExtra("PayBean");
        adapter = new DiscountAdapter(R.layout.layout_discount_item);
        if (mPayBean!=null){
            if (mPayBean.getData().getYouhuiquan_info().size()>0){
                adapter.addData(mPayBean.getData().getYouhuiquan_info());
            }
        }

        rwDiscountPay.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                youhuiquanInfoBean= (PayBean.DataBean.YouhuiquanInfoBean) adapter.getItem(position);
                if (youhuiquanInfoBean.getItem_is_valid()==1){
                    mIntent.putExtra("HUI_PRICE", youhuiquanInfoBean);
                    setResult(Contants.RESULT_CODE, mIntent);
                    finish();
                }
            }
        });
        btnUseDiscount.setOnClickListener(v -> {
            mIntent.putExtra("HUI_PRICE", "NOUSE");
            setResult(Contants.RESULT_NO_CODE, mIntent);
            finish();
        });
    }
}
