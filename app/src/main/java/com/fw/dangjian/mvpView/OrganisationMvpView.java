package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.OrganisationBean;


public interface OrganisationMvpView extends BaseMvpView {
    void onGetDataNext(OrganisationBean actionBean);
}
