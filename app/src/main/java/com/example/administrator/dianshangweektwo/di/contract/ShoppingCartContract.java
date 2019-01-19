package com.example.administrator.dianshangweektwo.di.contract;

public interface ShoppingCartContract {

    public interface IView {

        public void showLoading();

        public void hideLoading();


        public void showData(String mCartString);
    }

    public interface IPresenter<T extends IView> {

        public void attachView(T t);


        public void detachView(T t);


        public void requestData();
    }
    public interface IModel {

        public void containData(OnCallBackLisenter onCallBackLisenter);


        public interface OnCallBackLisenter {
            public void onCallBack(String mCartString);
        }
    }
}
