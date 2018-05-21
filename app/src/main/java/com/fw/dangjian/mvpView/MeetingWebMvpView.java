package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.MeetingPrintBean;


public interface MeetingWebMvpView extends BaseMvpView {
    void onGetDataNext(MeetingPrintBean actionBean);
}
