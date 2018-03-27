package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.CourseBean;


public interface CourseMvpView extends BaseMvpView {
    void onGetDataNext(CourseBean courseBean);
}
