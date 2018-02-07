package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.FileBean;


public interface FileMvpView extends BaseMvpView {
    void onGetDataNext(FileBean kongBean);
}
