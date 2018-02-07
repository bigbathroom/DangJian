package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.LoginBean;
import com.fw.dangjian.mvpView.LoginMvpView;

import rx.Subscriber;

public class LoginPresenter extends BasePresenter {

    private LoginMvpView mvpView;

    public LoginPresenter(LoginMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void login(String username, String password) {
        retrofitHelper.toSubscribe(req.login(username, password), new Subscriber<LoginBean>() {
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
            public void onNext(LoginBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onGetDataNext(user);
            }
        });
    }


}
