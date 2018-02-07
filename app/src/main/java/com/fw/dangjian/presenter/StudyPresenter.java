package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.bean.StudyPageBean;
import com.fw.dangjian.mvpView.HomeMvpView;
import com.fw.dangjian.mvpView.StudyMvpView;

import rx.Subscriber;


public class StudyPresenter extends BasePresenter {

    public StudyPresenter() {
    }

    public void getColunm(final StudyMvpView studyMvpView) {
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
                studyMvpView.onGetColunmDataNext(studyBean);
            }
        });
    }

   public void getStudyPage(int columnid,int pageNum, final StudyMvpView studyMvpView) {

        retrofitHelper.toSubscribe(req.getStudyPage(columnid, pageNum), new Subscriber<StudyPageBean>() {
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
            public void onNext(StudyPageBean homeBean) {
                studyMvpView.onGetDataNext(homeBean);
            }
        });
    }






    public void getHomePage1(int pageNum, String name,final HomeMvpView homeMvpView) {

        retrofitHelper.toSubscribe(req.getHomePage1(pageNum,name), new Subscriber<HomeBean>() {
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
            public void onNext(HomeBean homeBean) {
                homeMvpView.onGetDataNext(homeBean);
            }
        });
    }

}
