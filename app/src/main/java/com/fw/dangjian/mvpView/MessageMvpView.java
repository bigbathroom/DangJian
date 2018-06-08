package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.MessageBean;


public interface MessageMvpView extends BaseMvpView {
    void onGetDataNext(MessageBean messageBean);
}
