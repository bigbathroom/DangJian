package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.ScoreBean;


public interface ScoreMvpView extends BaseMvpView {
    void onGetDataNext(ScoreBean scoreBean);
}
