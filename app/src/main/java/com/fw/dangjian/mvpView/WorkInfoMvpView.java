package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;

/**
 * 作者：haoran   on https://github.com/woaigmz 2017/8/8.
 * 邮箱：1549112908@qq.com
 * 说明：可以增加新功能
 */

public interface WorkInfoMvpView extends BaseMvpView {
    void onGetDataNext(KongBean kongBean);
    void onGetCommentNext(CommentBean kongBean);
    void onCommentNext(KongBean kongBean);
    void onThumbNext(KongBean kongBean);
}
