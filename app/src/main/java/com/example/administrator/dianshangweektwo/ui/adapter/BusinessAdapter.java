package com.example.administrator.dianshangweektwo.ui.adapter;


import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dianshangweektwo.R;
import com.example.administrator.dianshangweektwo.data.beans.ShoppingCartBean;

import java.util.List;

public class BusinessAdapter extends BaseQuickAdapter<ShoppingCartBean.DataBean, BaseViewHolder> {
    OnBusinessItemClickLisenter onBusinessItemClickLisenter;

    public interface OnBusinessItemClickLisenter {
        public void onCallBack();
    }

    public void setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter) {
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }

    public BusinessAdapter(int layoutResId, @Nullable List<ShoppingCartBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingCartBean.DataBean item) {

        helper.setText(R.id.tv_business_name, item.getSellerName());
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        final CheckBox cb_business = helper.getView(R.id.cb_business);
        cb_business.setOnCheckedChangeListener(null);

        cb_business.setChecked(item.getBusinessChecked());
        List<ShoppingCartBean.DataBean.ListBean> goodsList = item.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.recycler_goods_item, goodsList);
        rv_goods.setLayoutManager(manager);
        rv_goods.setAdapter(goodsAdapter);

        goodsAdapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                cb_business.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }
                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });


    }
}
