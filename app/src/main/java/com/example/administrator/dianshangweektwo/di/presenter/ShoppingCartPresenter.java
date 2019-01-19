package com.example.administrator.dianshangweektwo.di.presenter;

import com.example.administrator.dianshangweektwo.di.contract.ShoppingCartContract;
import com.example.administrator.dianshangweektwo.di.model.ShoppinCartModel;

import java.lang.ref.SoftReference;

public class ShoppingCartPresenter implements ShoppingCartContract.IPresenter<ShoppingCartContract.IView>  {
    ShoppingCartContract.IView iView;
    private SoftReference<ShoppingCartContract.IView> softReference;
    private ShoppingCartContract.IModel model;

    @Override
    public void attachView(ShoppingCartContract.IView iView) {
        this.iView = iView;
        //软引用
        softReference = new SoftReference<>(iView);
        //获取M层
        model = new ShoppinCartModel();
    }

    @Override
    public void detachView(ShoppingCartContract.IView iView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        //给用户等待提示
        iView.showLoading();
        model.containData(new ShoppingCartContract.IModel.OnCallBackLisenter() {
            @Override
            public void onCallBack(String mCartString) {
                //去掉进度条
                iView.hideLoading();
                //数据回显
                iView.showData(mCartString);
            }
        });
    }
}
