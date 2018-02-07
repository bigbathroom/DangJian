package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.BoxBean;
import com.fw.dangjian.bean.BoxPageBean;


public interface BoxMvpView extends BaseMvpView {
    void onGetColunmDataNext(BoxBean boxBean);
    void onGetDataNext(BoxPageBean boxPageBean);
}
