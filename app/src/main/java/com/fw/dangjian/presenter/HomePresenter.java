package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;
import com.fw.dangjian.mvpView.HomeMvpView;

import rx.Subscriber;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/7/31.
 * 邮箱：1549112908@qq.com
 * 说明：
 */

public class HomePresenter extends BasePresenter {

    public HomePresenter() {
    }

    public void getColunm(final HomeMvpView homeMvpView) {
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
                homeMvpView.onGetColunmDataNext(columnBean);
            }
        });
    }

   public void getHomePage(int columnid,int pageNum, final HomeMvpView homeMvpView) {

        retrofitHelper.toSubscribe(req.getHomePage(columnid, pageNum), new Subscriber<HomeBean>() {
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
