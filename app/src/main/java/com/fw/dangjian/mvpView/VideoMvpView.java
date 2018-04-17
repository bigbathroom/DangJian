package com.fw.dangjian.mvpView;

import com.fw.dangjian.base.BaseMvpView;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.NoteBean;
import com.fw.dangjian.bean.VideoBean;


public interface VideoMvpView extends BaseMvpView {
    void onGetDataNext(VideoBean kongBean);
    void onCommentNext(KongBean kongBean);
    void onThumbNext(KongBean kongBean);
    void onGetCommentNext(CommentBean kongBean);
    void onNoteNext(KongBean kongBean);
    void onGetNoteNext(NoteBean kongBean);
}
