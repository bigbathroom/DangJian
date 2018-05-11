package com.fw.dangjian.presenter;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.mvpView.PutPasswordMvpView;

/**
 * Created by qhc on 2017/9/16.
 */

public class SendNewPasswordPresenter extends BasePresenter {

    private PutPasswordMvpView MvpView;
    private String mytoken = "";


    public SendNewPasswordPresenter(PutPasswordMvpView putPasswordMvpView) {
        MvpView = putPasswordMvpView;
    }

    /*publics void SendNewPassword(String oldpassword, String newpassword) {
        retrofitHelper.toSubscribe(req.reset(oldpassword, newpassword), new Subscriber<KongBean>() {
            @Override
            publics void onCompleted() {
                MvpView.onGetDataCompleted();
            }

            @Override
            publics void onError(Throwable e) {
                MvpView.onGetDataError(e);
            }

            @Override
            publics void onNext(KongBean kongBean) {
                MvpView.onPutPasswordNext(kongBean);
            }
        });
    }*/

}
