package com.fw.dangjian.base;

/**
 * Created by Administrator on 2017/11/6.
 */

public interface BaseMvpView {
    void onGetDataCompleted();
    void onGetDataError(Throwable e);
}
