package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.IdentifyCode;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.RegistBean;
import com.fw.dangjian.mvpView.RegistMvpView;

import rx.Subscriber;

public class RegistPresenter extends BasePresenter {

    private RegistMvpView mvpView;

    public RegistPresenter(RegistMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void regist(String username, String password,String yzm) {
        retrofitHelper.toSubscribe(req.regist(username, password,yzm), new Subscriber<RegistBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000","loginonCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "loginononError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(RegistBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onRegisterNext(user);
            }
        });
    }

    public void getIdentifyMessge(String username,int type) {
        retrofitHelper.toSubscribe(req.sendCode(username,type), new Subscriber<IdentifyCode>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(IdentifyCode identifyCode) {
                mvpView.onGetIdentifyCodeNext(identifyCode);
        }
        });

    }


    public void forget(String username, String password,String yzm) {
        retrofitHelper.toSubscribe(req.forget(username, password,yzm), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000","loginonCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "loginononError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onForgetPswNext(user);
            }
        });
    }

}
