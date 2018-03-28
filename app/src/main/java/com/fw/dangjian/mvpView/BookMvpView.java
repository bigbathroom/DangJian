package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.BookBean;


public interface BookMvpView extends BaseMvpView {
    void onGetDataNext(BookBean bookBean);
}
