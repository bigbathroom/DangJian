package com.fw.dangjian.presenter;

import android.util.Log;

import com.fw.dangjian.base.BasePresenter;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.NoteBean;
import com.fw.dangjian.bean.SubmitBean1;
import com.fw.dangjian.bean.VideoBean;
import com.fw.dangjian.mvpView.VideoMvpView;

import rx.Subscriber;

public class VideoInfoPresenter extends BasePresenter {

    private VideoMvpView mvpView;

    public VideoInfoPresenter(VideoMvpView mvpView) {
        this.mvpView = mvpView;
    }

    public void getVideo(int id) {
        retrofitHelper.toSubscribe(req.getVideo(id), new Subscriber<VideoBean>() {
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
            public void onNext(VideoBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onGetDataNext(user);
            }
        });
    }


    public void submitNote(int managerid,int postId,String content) {
        retrofitHelper.toSubscribe(req.submitNote(managerid,postId,content), new Subscriber<SubmitBean1>() {
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
            public void onNext(SubmitBean1 user) {
                Log.d("000000", "loginononNext");
                mvpView.onNoteNext(user);
            }
        });
    }

    public void changeNote(int managerid,int Id,String content) {
        retrofitHelper.toSubscribe(req.changeNote(managerid,Id,content), new Subscriber<SubmitBean1>() {
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
            public void onNext(SubmitBean1 user) {
                Log.d("000000", "loginononNext");
                mvpView.onNoteNext(user);
            }
        });
    }
    public void getNote(int managerid,int comment_postid) {
        retrofitHelper.toSubscribe(req.getNote(managerid,comment_postid), new Subscriber<NoteBean>() {
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
            public void onNext(NoteBean user) {
                Log.d("000000", "loginononNext");
                mvpView.onGetNoteNext(user);
            }
        });
    }


    public void commitComment1(int comment_postid,String comment_author,String comment_content,int managerid) {
        retrofitHelper.toSubscribe(req.submitLearnComment1(comment_postid,comment_author,comment_content,managerid), new Subscriber<KongBean>() {
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
