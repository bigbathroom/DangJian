package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.DjeBean;


public interface DJEMvpView extends BaseMvpView {
    void onGetDataNext(DjeBean djeBean);
}
