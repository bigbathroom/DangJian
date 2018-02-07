package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.KongBean;

/**
 * Created by qhc on 2017/9/16.
 */

public interface PutPasswordMvpView extends BaseMvpView{
    void onPutPasswordNext(KongBean messge);
}
