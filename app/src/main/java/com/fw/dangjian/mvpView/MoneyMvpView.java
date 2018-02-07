package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.MoneyBean;


public interface MoneyMvpView extends BaseMvpView {
    void onGetDataNext(MoneyBean homeBean);
}
