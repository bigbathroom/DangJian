package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.CourseBean;
import com.fw.dangjian.mvpView.CourseMvpView;

import rx.Subscriber;



public class CoursePresenter extends BasePresenter {


   public void getCoursePage(int pageNum, final CourseMvpView courseMvpView) {

        retrofitHelper.toSubscribe(req.getCourse(pageNum), new Subscriber<CourseBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                courseMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                courseMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(CourseBean courseBean) {
                courseMvpView.onGetDataNext(courseBean);
            }
        });
    }


}
