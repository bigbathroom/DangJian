package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.WaterBean;
import com.fw.dangjian.mvpView.BranchMvpView;

import rx.Subscriber;


public class BranchPresenter extends BasePresenter {


   public void getWaterPage(int pageNum, int managerid,final BranchMvpView branchMvpView) {

        retrofitHelper.toSubscribe(req.getWater(pageNum,managerid), new Subscriber<WaterBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000", "onCompleted");
                branchMvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "onError");
                branchMvpView.onGetDataError(e);
            }

            @Override
            public void onNext(WaterBean waterBean) {
                branchMvpView.onGetDataNext(waterBean);
            }
        });
    }


}
