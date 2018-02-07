package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.BoxBean;
import com.fw.dangjian.bean.BoxPageBean;
import com.fw.dangjian.mvpView.BoxMvpView;

import rx.Subscriber;


public class BoxPresenter extends BasePresenter {

    public BoxPresenter() {
    }

    public void getColunm(final BoxMvpView boxMvpView) {
        retrofitHelper.toSubscribe(req.getBoxColumn(), new Subscriber<BoxBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                boxMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                boxMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(BoxBean boxBean) {
                boxMvpView.onGetColunmDataNext(boxBean);
            }
        });
    }



    public void getBoxPage(int columnid, int pageNum, final BoxMvpView boxMvpView) {

        retrofitHelper.toSubscribe(req.getBoxPage(columnid, pageNum), new Subscriber<BoxPageBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                boxMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                boxMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(BoxPageBean homeBean) {
                boxMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
