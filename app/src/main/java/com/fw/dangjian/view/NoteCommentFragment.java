package com.fw.dangjian.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.PingLunAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.bean.NoteBean;
import com.fw.dangjian.bean.SubmitBean1;
import com.fw.dangjian.bean.VideoBean;
import com.fw.dangjian.dialog.BookDialog;
import com.fw.dangjian.dialog.CommentDialog;
import com.fw.dangjian.mvpView.VideoMvpView;
import com.fw.dangjian.presenter.VideoInfoPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.fw.dangjian.netUtil.Constants.BASE_URL;

public class NoteCommentFragment extends BaseFragment implements VideoMvpView {

    @BindView(R.id.iv_comment)
    ImageView iv_comment;
    @BindView(R.id.book)
    TextView book;
    @BindView(R.id.iv_praise)
    ImageView iv_praise;
    @BindView(R.id.iv_share)
    ImageView iv_share;
    @BindView(R.id.tv_comment_count)
    TextView tv_comment_count;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;

    @BindView(R.id.no_content)
    RelativeLayout no_content;

    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;

    @BindView(R.id.rl_book)
    RelativeLayout rl_book;
    @BindView(R.id.et_biji)
    EditText et_biji;
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.tv_sure)
    TextView tv_sure;


    private int studyId;
    private int managerId;
    private PingLunAdapter mAdapter;
    private int count = 0;
    private VideoInfoPresenter videoInfoPresenter;
    private CommentDialog commentDialog;


    private Intent intent;
    private List<CommentBean.ResultBean> lists;
    private BookDialog bookDialog;
    private String content ="";
    private int noteId = -1;


    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.fragment_note_comment, null);
    }

    public static NoteCommentFragment newInstance(int studyId) {
        NoteCommentFragment fragment = new NoteCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("studyId", studyId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initUi() {
        studyId = getArguments().getInt("studyId");
        managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

        videoInfoPresenter = new VideoInfoPresenter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);

        videoInfoPresenter.getComment(studyId);
    }

    private void initAdapterClike() {

        mAdapter.setonItemClickLitener(new PingLunAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @OnClick({ R.id.iv_comment, R.id.book,R.id.iv_praise, R.id.iv_share, R.id.rl_comment, R.id.tv_cancle, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_praise:
                videoInfoPresenter.thumb(studyId);
                break;
            case R.id.iv_share:
                String  url = BASE_URL + "/note/" + studyId + "?managerid=" + managerId;
                UMWeb web = new UMWeb(url);
                web.setTitle("党建");//标题
                web.setThumb(new UMImage(getActivity(), R.mipmap.thumb));  //缩略图
                web.setDescription("实时发布党新闻和活动");//描述
                new ShareAction(getActivity()).withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
                break;

            case R.id.iv_comment:
                if (managerId == -1) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1000);
                } else {
                    commentDialog = new CommentDialog(getActivity());
                    commentDialog.show();
                    commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                        @Override
                        public void onCommit(EditText et, View v) {
                            String s = et.getText().toString();
                            if (TextUtils.isEmpty(s)) {
                                ToastUtils.show(getActivity(), "请先输入评论", Toast.LENGTH_SHORT);
                            }
                            videoInfoPresenter.commitComment1(studyId, "", s, managerId);

                        }
                    });
                }

                break;
            case R.id.rl_comment:
                if (managerId == -1) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1000);
                } else {
                    commentDialog = new CommentDialog(getActivity());
                    commentDialog.show();
                    commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                        @Override
                        public void onCommit(EditText et, View v) {
                            String s = et.getText().toString();
                            if (TextUtils.isEmpty(s)) {
                                ToastUtils.show(getActivity(), "请先输入评论", Toast.LENGTH_SHORT);
                            }
                            videoInfoPresenter.commitComment1(studyId, "", s, managerId);
                        }
                    });
                }


                break;

            case R.id.book:
                if (managerId == -1) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1000);
                } else {
                    videoInfoPresenter.getNote(managerId,studyId);

                    rl_book.setVisibility(View.VISIBLE);

                   /* new Handler().postDelayed(new Runnable(){
                        publics void run() {
                            bookDialog = new BookDialog(VideoActivity.this,content);
                            bookDialog.show();
                            bookDialog.setOnCommitListener(new BookDialog.OnCommitListener() {
                                @Override
                                publics void onCommit(EditText et, View v) {
                                    String s = et.getText().toString();

                                    if (TextUtils.isEmpty(s)) {
                                        ToastUtils.show(VideoActivity.this, "请先输入笔记", Toast.LENGTH_SHORT);
                                    }else{

                                        if(noteId == -1||noteId == 0){
                                            videoInfoPresenter.submitNote(managerId,studyId,s);
                                        }else{
                                            videoInfoPresenter.changeNote(managerId,noteId,s);
                                        }

                                    }


                                }
                            });
                        }
                    }, 500);*/
                }
                break;

            case R.id.tv_cancle:
                et_biji.setText("");
                rl_book.setVisibility(View.GONE);

                break;
            case R.id.tv_sure:

                String s = et_biji.getText().toString();

                if (TextUtils.isEmpty(s)) {
                    ToastUtils.show(getActivity(), "请先输入笔记", Toast.LENGTH_SHORT);
                }else{

                    if(noteId == -1||noteId == 0){
                        videoInfoPresenter.submitNote(managerId,studyId,s);
                    }else{
                        videoInfoPresenter.changeNote(managerId,noteId,s);
                    }

                }

                break;
        }
    }

    @Override
    public void onGetNoteNext(NoteBean kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            if(kongBean.result!=null){
                content = kongBean.result.content;
                noteId = kongBean.result.id;
                et_biji.setText(content);
            }
        } else {
//            ToastUtils.show(this, "获取笔记失败", Toast.LENGTH_SHORT);
        }
    }


    @Override
    public void onCommentNext(KongBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            commentDialog.dismiss();
            videoInfoPresenter.getComment(studyId);
            ToastUtils.show(getActivity(), "提交成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(getActivity(), "提交评论失败", Toast.LENGTH_SHORT);
        }

    }


    @Override
    public void onGetCommentNext(CommentBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {

            if (kongBean.result != null && kongBean.result.size() > 0) {
                lists = kongBean.result;
                mAdapter = new PingLunAdapter(lists, getActivity());
                nrecycler.setAdapter(mAdapter);
                initAdapterClike();
                no_content.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);
            } else {
                no_content.setVisibility(View.VISIBLE);
                nrecycler.setVisibility(View.GONE);
            }

        } else {
            ToastUtils.show(getActivity(), kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onNoteNext(SubmitBean1 kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
//            bookDialog.dismiss();
            rl_book.setVisibility(View.GONE);
            ToastUtils.show(getActivity(), "提交成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(getActivity(), "提交失败", Toast.LENGTH_SHORT);
        }
    }


    @Override
    public void onThumbNext(KongBean kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            iv_praise.setImageResource(R.mipmap.praise01);
        } else {
            ToastUtils.show(getActivity(), "点赞失败", Toast.LENGTH_SHORT);
        }
    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            managerId = (int) SPUtils.get(getActivity(), ConstanceValue.LOGIN_TOKEN, -1);

        }else{
            UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
        }

    }


    @Override
    protected void initData() {

    }



    @Override
    public void onGetDataNext(VideoBean kongBean) {

    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "分享成功", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消", Toast.LENGTH_LONG).show();

        }
    };
}
