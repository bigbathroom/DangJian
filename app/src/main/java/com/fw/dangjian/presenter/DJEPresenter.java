package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.DjeBean;
import com.fw.dangjian.mvpView.DJEMvpView;

import rx.Subscriber;

public class DJEPresenter extends BasePresenter {

    public DJEPresenter() {
    }


   public void getDJe(int pageNum, final DJEMvpView djeMvpView) {
        retrofitHelper.toSubscribe(req.getDJEPage(17,pageNum), new Subscriber<DjeBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                djeMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                djeMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(DjeBean homeBean) {
                djeMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
