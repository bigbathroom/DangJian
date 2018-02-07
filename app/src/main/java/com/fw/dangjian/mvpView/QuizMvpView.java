package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.QuizBean;
import com.fw.dangjian.bean.SubmitBean;


public interface QuizMvpView extends BaseMvpView {
    void onGetDataNext(QuizBean actionBean);
    void onSubmitDataNext(SubmitBean submitBean);
}
