package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.IdentifyCode;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.RegistBean;

/**
 */

public interface RegistMvpView extends BaseMvpView {
    void onRegisterNext(RegistBean kongBean);

    void  onGetIdentifyCodeNext(IdentifyCode identifyCode);

    void onForgetPswNext(KongBean kongBean);

}
