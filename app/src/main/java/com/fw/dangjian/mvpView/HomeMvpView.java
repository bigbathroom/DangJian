package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.HomeBean;


public interface HomeMvpView extends BaseMvpView {
    void onGetColunmDataNext(ColumnBean columnBean);
    void onGetDataNext(HomeBean homeBean);
}
