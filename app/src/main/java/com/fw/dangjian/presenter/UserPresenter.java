package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.MineBean;
import com.fw.dangjian.bean.TotalScoreBean;
import com.fw.dangjian.mvpView.ChangeNameMvpView;
import com.fw.dangjian.mvpView.PutPasswordMvpView;
import com.fw.dangjian.mvpView.TotalScoreMvpView;
import com.fw.dangjian.mvpView.UserCenterMvpView;
import com.fw.dangjian.util.RetrofitParameterBuilder;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscriber;


public class UserPresenter extends BasePresenter {

    public UserPresenter() {
    }

    public void getUserProfile(int managerid,final UserCenterMvpView userCenterMvpView) {
        retrofitHelper.toSubscribe(req.getUserProfile(managerid), new Subscriber<MineBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                userCenterMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                userCenterMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(MineBean userProfile) {
                Log.d("000000", "onNext " + userProfile);
                userCenterMvpView.setUserData(userProfile);
            }
        });
    }


    public void saveUserName(int managerid,String name, final ChangeNameMvpView changeNameMvpView) {

        retrofitHelper.toSubscribe(req.saveUserName(managerid, name), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                changeNameMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                changeNameMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean userNameBean) {
                changeNameMvpView.onsetDataNext(userNameBean);
            }
        });
    }


    public void uploadImg(int managerId,File imgFile, final UserCenterMvpView userCenterMvpView) {

        Map<String, RequestBody> params = RetrofitParameterBuilder.newBuilder()
                .addParameter("file", imgFile)
                .bulider();


        retrofitHelper.toSubscribe(req.uploadUserImg(managerId,params), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("dddd", "success");
                userCenterMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("dddd", e.toString());
                userCenterMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean emptyMessage) {
                Log.d("dddd", "successd");
                userCenterMvpView.uploadImg(emptyMessage);
            }
        });
    }

    public void changePsw(int managerid,String oldpwd, String password,final PutPasswordMvpView changeNameMvpView) {

        retrofitHelper.toSubscribe(req.reset(managerid, oldpwd,password), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                changeNameMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                changeNameMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean userNameBean) {
                changeNameMvpView.onPutPasswordNext(userNameBean);
            }
        });
    }

    public void getTotalScore(int managerid,final TotalScoreMvpView userCenterMvpView) {
        retrofitHelper.toSubscribe(req.getTotalScore(managerid), new Subscriber<TotalScoreBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                userCenterMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                userCenterMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(TotalScoreBean userProfile) {
                Log.d("000000", "onNext " + userProfile);
                userCenterMvpView.onGetDataNext(userProfile);
            }
        });
    }


}
