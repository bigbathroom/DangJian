package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.BoardBean;


public interface BoardMvpView extends BaseMvpView {
    void onGetDataNext(BoardBean boardBean);
}
