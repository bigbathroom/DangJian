package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.ColumnBean;
import com.fw.dangjian.bean.StudyBean;


public interface FaBuMvpView extends BaseMvpView {
    void onGetHomeColunmDataNext(ColumnBean columnBean);
    void onGetStudyColunmDataNext(StudyBean studyBean);

}
