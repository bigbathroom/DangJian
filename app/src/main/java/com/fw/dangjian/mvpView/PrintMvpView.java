package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.PrintHtmlBean;


public interface PrintMvpView extends BaseMvpView {
    void onGetLearnNext(PrintHtmlBean actionBean);
    void onGetMeetingNext(PrintHtmlBean actionBean);
}
