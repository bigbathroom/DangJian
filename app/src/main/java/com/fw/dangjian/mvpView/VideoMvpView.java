package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;


public interface VideoMvpView extends BaseMvpView {
    void onGetDataNext(KongBean kongBean);

    void onCommentNext(KongBean kongBean);
    void onThumbNext(KongBean kongBean);
    void onGetCommentNext(CommentBean kongBean);
}
