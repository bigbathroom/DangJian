package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.mvpView.FaBuMvpView;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/11.
 */

public class FabuPresenter extends BasePresenter {
    public FabuPresenter() {
    }

    public void getHomeColunm(final FaBuMvpView homeMvpView) {
        retrofitHelper.toSubscribe(req.getHomeColumn(), new Subscriber<ColumnBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                homeMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                homeMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(ColumnBean columnBean) {
                Log.d("000000", "onNext " + columnBean);
                homeMvpView.onGetHomeColunmDataNext(columnBean);
            }
        });
    }

    public void getStudyColunm(final FaBuMvpView studyMvpView) {
        retrofitHelper.toSubscribe(req.getStudyColumn(), new Subscriber<StudyBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                studyMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                studyMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(StudyBean studyBean) {
                Log.d("000000", "onNext " + studyBean);
                studyMvpView.onGetStudyColunmDataNext(studyBean);
            }
        });
    }


}
