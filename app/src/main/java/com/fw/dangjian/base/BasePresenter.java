package com.fw.dangjian.base;


import com.fw.dangjian.netUtil.AppService;
import com.fw.dangjian.netUtil.RetrofitHelper;

/**
 * Created by Administrator on 2017/11/6.
 */

public class BasePresenter {
    protected RetrofitHelper retrofitHelper;
    protected AppService req;

    public BasePresenter() {
        retrofitHelper = RetrofitHelper.getRetrofitHelper();
        req = retrofitHelper.createReq(AppService.class);
    }
}
