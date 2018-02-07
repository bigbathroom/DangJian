package com.fw.dangjian.presenter;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.QuizBean;
import com.fw.dangjian.bean.SubmitBean;
import com.fw.dangjian.mvpView.QuizMvpView;

import rx.Subscriber;

/**
 * Created by qhc on 2017/9/22.
 */

public class QuizPersenter extends BasePresenter {
    QuizMvpView mvpView;

    public QuizPersenter(QuizMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void GetQuestion(int squareid) {
        retrofitHelper.toSubscribe(req.getQuestion(squareid), new Subscriber<QuizBean>() {
            @Override
            public void onCompleted() {
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(QuizBean actionBean) {
                mvpView.onGetDataNext(actionBean);
            }
        });
    }


    public void submitAnswer(int squareid,String answer){
        retrofitHelper.toSubscribe(req.submitQuestion(squareid,answer), new Subscriber<SubmitBean>() {
            @Override
            public void onCompleted() {
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(SubmitBean submitBean) {
                mvpView.onSubmitDataNext(submitBean);
            }
        });
    }
}
