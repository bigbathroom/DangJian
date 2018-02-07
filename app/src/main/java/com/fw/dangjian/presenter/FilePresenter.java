package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.FileBean;
import com.fw.dangjian.mvpView.FileMvpView;

import rx.Subscriber;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/7/31.
 * 邮箱：1549112908@qq.com
 * 说明：
 */

public class FilePresenter extends BasePresenter {


   public void getFile(int managerId, final FileMvpView actionMvpView) {

        retrofitHelper.toSubscribe(req.getFile(managerId), new Subscriber<FileBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                actionMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                actionMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(FileBean homeBean) {
                actionMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
