package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.ActionBean;


public interface ActionMvpView extends BaseMvpView {
    void onGetDataNext(ActionBean actionBean);
}
