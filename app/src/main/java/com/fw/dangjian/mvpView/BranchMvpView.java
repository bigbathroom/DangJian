package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.WaterBean;


public interface BranchMvpView extends BaseMvpView {
    void onGetDataNext(WaterBean waterBean);
}
