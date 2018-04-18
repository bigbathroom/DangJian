package com.fw.dangjian.view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.adapter.PingLunAdapter;
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
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity implements VideoMvpView {
    @BindView(R.id.iv_back)
    RelativeLayout left;
    @BindView(R.id.video_view)
    NiceVideoPlayer mNiceVideoPlayer;
    @BindView(R.id.tv_introduce_content1)
    TextView tv_introduce_content1;
    @BindView(R.id.tv_introduce_content2)
    TextView tv_introduce_content2;
    @BindView(R.id.tv_introduce)
    TextView tv_introduce;
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
    @BindView(R.id.tv_comment)
    TextView tv_comment;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;

    @BindView(R.id.no_content)
    RelativeLayout no_content;

    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;

    private PingLunAdapter mAdapter;
    private int count = 0;
    private VideoInfoPresenter videoInfoPresenter;
    private CommentDialog commentDialog;

    private Intent intent;
    private int studyId;
    private int managerId;
    private List<CommentBean.ResultBean> lists;
    private String shareUrl;
    private BookDialog bookDialog;
    private String content ="";
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_portrait);
        ButterKnife.bind(this);
        MyApplication.getInstance().addActivity(this);

        videoInfoPresenter = new VideoInfoPresenter(this);

        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        intent = getIntent();
        if (intent != null) {
            studyId = intent.getIntExtra("studyId", -1);
        }

        videoInfoPresenter.getVideo(studyId);

        initUi();

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

    }

    private void initUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

    @OnClick({R.id.iv_back, R.id.tv_introduce, R.id.iv_comment, R.id.book,R.id.iv_praise, R.id.iv_share, R.id.tv_comment, R.id.rl_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_introduce:
                count++;
                if (count % 2 == 1) {
                    tv_introduce_content1.setVisibility(View.GONE);
                    tv_introduce_content2.setVisibility(View.VISIBLE);
                } else {
                    tv_introduce_content1.setVisibility(View.VISIBLE);
                    tv_introduce_content2.setVisibility(View.GONE);
                }

                break;
            case R.id.iv_praise:
                videoInfoPresenter.thumb(studyId);
                break;
            case R.id.iv_share:
                UMWeb web = new UMWeb(shareUrl);
                web.setTitle("党建");//标题
                web.setThumb(new UMImage(this, R.mipmap.thumb));  //缩略图
                web.setDescription("实时发布党新闻和活动");//描述
                new ShareAction(this).withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
                break;
            case R.id.tv_comment:

                break;

            case R.id.iv_comment:
                if (managerId == -1) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    commentDialog = new CommentDialog(this);
                    commentDialog.show();
                    commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                        @Override
                        public void onCommit(EditText et, View v) {
                            String s = et.getText().toString();
                            if (TextUtils.isEmpty(s)) {
                                ToastUtils.show(VideoActivity.this, "请先输入评论", Toast.LENGTH_SHORT);
                            }
                            videoInfoPresenter.commitComment1(studyId, "", s, managerId);

                        }
                    });
                }

                break;
            case R.id.book:

                if (managerId == -1) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {

                    videoInfoPresenter.getNote(managerId,studyId);
                }


                break;
            case R.id.rl_comment:
                if (managerId == -1) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    commentDialog = new CommentDialog(this);
                    commentDialog.show();
                    commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                        @Override
                        public void onCommit(EditText et, View v) {
                            String s = et.getText().toString();
                            if (TextUtils.isEmpty(s)) {
                                ToastUtils.show(VideoActivity.this, "请先输入评论", Toast.LENGTH_SHORT);
                            }
                            videoInfoPresenter.commitComment1(studyId, "", s, managerId);
                        }
                    });
                }


                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }


    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {

    }

    @Override
    public void onGetDataNext(VideoBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            if (kongBean.result != null) {
                String mVideoUrl = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";

                if (kongBean.result.post_content == null || kongBean.result.post_content.equals("")) {
//                    mNiceVideoPlayer.setUp(mVideoUrl, null);
                    shareUrl="https://www.baidu.com";
                } else {
                    shareUrl = kongBean.result.post_content;
                    mNiceVideoPlayer.setUp(kongBean.result.post_content, null);
                }

              mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE); // or NiceVideoPlayer.TYPE_IJK
                TxVideoPlayerController controller = new TxVideoPlayerController(this);
//              controller.setTitle(mTitle);
//              controller.setImage(mImageUrl);
                mNiceVideoPlayer.setController(controller);

                mNiceVideoPlayer.start();


                tv_introduce_content1.setText(kongBean.result.post_excerpt);
                tv_introduce_content2.setText(kongBean.result.post_excerpt);
            }

        } else {
            ToastUtils.show(this, kongBean.result_msg, Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void onCommentNext(KongBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            commentDialog.dismiss();
            videoInfoPresenter.getComment(studyId);
            ToastUtils.show(this, "提交成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(this, "提交评论失败", Toast.LENGTH_SHORT);
        }

    }


    @Override
    public void onGetCommentNext(CommentBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {

            if (kongBean.result != null && kongBean.result.size() > 0) {
                lists = kongBean.result;
                mAdapter = new PingLunAdapter(lists, this);
                nrecycler.setAdapter(mAdapter);
                initAdapterClike();
                no_content.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);
            } else {
                no_content.setVisibility(View.VISIBLE);
                nrecycler.setVisibility(View.GONE);
            }

        } else {
            ToastUtils.show(this, kongBean.result_msg, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onNoteNext(SubmitBean1 kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            bookDialog.dismiss();
            ToastUtils.show(this, "提交成功", Toast.LENGTH_SHORT);
        } else {
            ToastUtils.show(this, "提交失败", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onGetNoteNext(NoteBean kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            content = kongBean.result.content;
            noteId = kongBean.result.id;
            bookDialog = new BookDialog(this,content);
            bookDialog.show();
            bookDialog.setOnCommitListener(new BookDialog.OnCommitListener() {
                @Override
                public void onCommit(EditText et, View v) {
                    String s = et.getText().toString();
                    if (TextUtils.isEmpty(s)) {
                        ToastUtils.show(VideoActivity.this, "请先输入笔记", Toast.LENGTH_SHORT);
                    }

                    if(noteId == -1){
                        videoInfoPresenter.submitNote(managerId,studyId,s);
                    }else{
                        videoInfoPresenter.changeNote(managerId,noteId,s);
                    }

                }
            });
        } else {
            ToastUtils.show(this, "获取笔记失败", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onThumbNext(KongBean kongBean) {
        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            iv_praise.setImageResource(R.mipmap.praise01);
        } else {
            ToastUtils.show(this, "点赞失败", Toast.LENGTH_SHORT);
        }
    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
            Toast.makeText(VideoActivity.this, "分享成功", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(VideoActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(VideoActivity.this, "取消", Toast.LENGTH_LONG).show();

        }
    };


}
