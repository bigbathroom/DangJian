package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.TotalScoreBean;


public interface TotalScoreMvpView extends BaseMvpView {
    void onGetDataNext(TotalScoreBean scoreBean);
}
