package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.StudyBean;
import com.fw.dangjian.bean.StudyPageBean;


public interface StudyMvpView extends BaseMvpView {
    void onGetColunmDataNext(StudyBean studyBean);
    void onGetDataNext(StudyPageBean studyPageBean);
}
