package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.AllNoteBean;


public interface AllNoteMvpView extends BaseMvpView {
    void onGetDataNext(AllNoteBean  noteBean);
}
