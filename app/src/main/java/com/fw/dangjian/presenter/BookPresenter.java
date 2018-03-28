package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.BookBean;
import com.fw.dangjian.mvpView.BookMvpView;

import rx.Subscriber;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/7/31.
 * 邮箱：1549112908@qq.com
 * 说明：
 */

public class BookPresenter extends BasePresenter {


   public void getBookPage(int pageNum, final BookMvpView bookMvpView) {

        retrofitHelper.toSubscribe(req.getBook(pageNum), new Subscriber<BookBean>() {
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
            public void onNext(BookBean homeBean) {
                bookMvpView.onGetDataNext(homeBean);
            }
        });
    }


}
