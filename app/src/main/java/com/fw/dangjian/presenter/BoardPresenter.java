package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.BoardBean;
import com.fw.dangjian.mvpView.BoardMvpView;

import rx.Subscriber;

public class BoardPresenter extends BasePresenter {


   public void getBoard(int managerid, final BoardMvpView bookMvpView) {

        retrofitHelper.toSubscribe(req.getBoard(managerid), new Subscriber<BoardBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                bookMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                bookMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(BoardBean homeBean) {
                bookMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
