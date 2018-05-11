package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.AllNoteBean;
import com.fw.dangjian.bean.KongBean;


public interface MeetingNoteMvpView extends BaseMvpView {
    void onGetDataNext(AllNoteBean noteBean);
    void onNoteNext(KongBean kongBean);
}
