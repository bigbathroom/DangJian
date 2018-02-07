package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.MoneyBean;
import com.fw.dangjian.mvpView.MoneyMvpView;

import rx.Subscriber;


public class MoneyPresenter extends BasePresenter {

    public MoneyPresenter() {
    }

    public void getMoney(double money,final MoneyMvpView moneyMvpView) {
        retrofitHelper.toSubscribe(req.getMoney(money), new Subscriber<MoneyBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                moneyMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                moneyMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(MoneyBean userProfile) {
                moneyMvpView.onGetDataNext(userProfile);
            }
        });
    }


}
