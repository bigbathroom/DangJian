package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.ActionBean;
import com.fw.dangjian.mvpView.ActionMvpView;

import rx.Subscriber;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/7/31.
 * 邮箱：1549112908@qq.com
 * 说明：
 */

public class ActionPresenter extends BasePresenter {


   public void getActionPage(int pageNum, final ActionMvpView actionMvpView) {

        retrofitHelper.toSubscribe(req.getAction(pageNum), new Subscriber<ActionBean>() {
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
            public void onNext(ActionBean homeBean) {
                actionMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
