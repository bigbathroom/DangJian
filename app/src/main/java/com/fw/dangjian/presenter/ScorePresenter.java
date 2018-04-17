package com.fw.dangjian.presenter;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.ScoreBean;
import com.fw.dangjian.mvpView.ScoreMvpView;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/4
 */

public class ScorePresenter extends BasePresenter{
    ScoreMvpView mvpView;

    public ScorePresenter(ScoreMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void GetResult(int managerId,int squareid) {
        retrofitHelper.toSubscribe(req.getResult(managerId,squareid), new Subscriber<ScoreBean>() {
            @Override
            public void onCompleted() {
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(ScoreBean actionBean) {
                mvpView.onGetDataNext(actionBean);
            }
        });
    }

    
}
