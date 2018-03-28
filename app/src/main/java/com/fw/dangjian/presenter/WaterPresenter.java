package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.WaterBean;
import com.fw.dangjian.mvpView.WaterMvpView;

import rx.Subscriber;


public class WaterPresenter extends BasePresenter {

   public void getBookPage(int pageNum,int managerid, final WaterMvpView bookMvpView) {

        retrofitHelper.toSubscribe(req.getWater(pageNum,managerid), new Subscriber<WaterBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                bookMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                bookMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(WaterBean homeBean) {
                bookMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
