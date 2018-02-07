package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.mvpView.VideoMvpView;

import rx.Subscriber;

public class VideoInfoPresenter extends BasePresenter {

    private VideoMvpView mvpView;

    public VideoInfoPresenter(VideoMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void commitComment(int comment_postid,String comment_author,String comment_content) {
        retrofitHelper.toSubscribe(req.submitComment1(comment_postid,comment_author,comment_content), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000","loginonCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "loginononError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onCommentNext(user);
            }
        });
    }

    public void getComment(int comment_postid) {
        retrofitHelper.toSubscribe(req.getComment(comment_postid), new Subscriber<CommentBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000","loginonCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "loginononError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(CommentBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onGetCommentNext(user);
            }
        });
    }



    public void thumb(int post_id) {
        retrofitHelper.toSubscribe(req.thumb1(post_id), new Subscriber<KongBean>() {
            @Override
            public void onCompleted() {
                Log.d("000000","loginonCompleted");
                mvpView.onGetDataCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("000000", "loginononError");
                mvpView.onGetDataError(e);
            }

            @Override
            public void onNext(KongBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onThumbNext(user);
            }
        });
    }
}
