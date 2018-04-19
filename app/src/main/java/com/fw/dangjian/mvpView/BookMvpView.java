package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.MeetBean;


public interface BookMvpView extends BaseMvpView {
    void onGetDataNext(MeetBean bookBean);
}
