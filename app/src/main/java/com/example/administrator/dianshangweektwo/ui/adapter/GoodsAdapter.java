package com.example.administrator.dianshangweektwo.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dianshangweektwo.R;
import com.example.administrator.dianshangweektwo.data.beans.ShoppingCartBean;
import com.example.administrator.dianshangweektwo.ui.layout.CalculatorView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingCartBean.DataBean.ListBean, BaseViewHolder> {
    OnGoodsItemClickLisenter onGoodsItemClickLisenter;

    public void setOnGoodsItemClickLisenter(OnGoodsItemClickLisenter onGoodsItemClickLisenter) {
        this.onGoodsItemClickLisenter = onGoodsItemClickLisenter;
    }

    //接口回调
    public interface OnGoodsItemClickLisenter {
        public void onCallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingCartBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCartBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goodsPrice, "￥：" + item.getPrice());
        helper.setText(R.id.tv_goodsTitle, item.getTitle());
        ImageView iv_goodsIcon = helper.getView(R.id.iv_goodsIcon);
        String imagesString = item.getImages();
        String[] imagesStr = imagesString.split("\\|");
        Glide.with(mContext).load(imagesStr[0]).into(iv_goodsIcon);
        //避免焦点抢占
        final CheckBox cb_goods = helper.getView(R.id.cb_goods);
        cb_goods.setOnCheckedChangeListener(null);
        cb_goods.setChecked(item.getGoodsChecked());
        //以接口的方式把状态回传给外层
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                onGoodsItemClickLisenter.onCallBack();
            }
        });
        /*CalculatorView calculatorView = helper.getView(R.id.cv_calculatorView);
        calculatorView.setOnCalCulatorLisenter(new CalculatorView.OnCalCulatorLisenter() {
            @Override
            public void onDecrese(int number) {
                item.setDefalutNumber(number);
                onGoodsItemClickLisenter.onCallBack();
            }

            @Override
            public void onAdd(int number) {
                //对新增的字段进行改动
                item.setDefalutNumber(number);
                onGoodsItemClickLisenter.onCallBack();
            }
        });*/
    }
}
