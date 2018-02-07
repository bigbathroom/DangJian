package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.LoginBean;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/8/8.
 * 邮箱：1549112908@qq.com
 * 说明：可以增加新功能
 */

public interface LoginMvpView extends BaseMvpView {
    void onGetDataNext(LoginBean kongBean);
}
