package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.MineBean;


public interface UserCenterMvpView extends BaseMvpView {

    void setUserData(MineBean userProfile);
    void uploadImg(KongBean userProfile);

}
