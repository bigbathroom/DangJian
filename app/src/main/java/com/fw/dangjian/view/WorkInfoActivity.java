package com.fw.dangjian.view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.adapter.PingLunAdapter;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.CommentBean;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.dialog.CommentDialog;
import com.fw.dangjian.mvpView.WorkInfoMvpView;
import com.fw.dangjian.netUtil.RetrofitHelper;
import com.fw.dangjian.presenter.WorkInfoPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.StringUtils;
import com.fw.dangjian.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.fw.dangjian.netUtil.Constants.BASE_URL;

public class WorkInfoActivity extends BaseActivity implements WorkInfoMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv)
    WebView wv;
    @BindView(R.id.rl_pinglun)
    RelativeLayout rl_pinglun;
    @BindView(R.id.recyclerview)
    RecyclerView nrecycler;
    @BindView(R.id.no_content)
    RelativeLayout linearLayout_no_content;
    @BindView(R.id.tv_kong)
    TextView tv_kong;
    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;
    @BindView(R.id.iv_commment)
    ImageView iv_commment;
    @BindView(R.id.iv_collect)
    ImageView iv_collect;
    @BindView(R.id.iv_praise)
    ImageView iv_praise;
    @BindView(R.id.iv_share)
    ImageView iv_share;
    private int id;
    private WorkInfoPresenter workInfoPresenter;
    private CommentDialog commentDialog;
    private int managerId;
    private String url;
    private String title;
    private String banner_url;

    private PingLunAdapter mAdapter;
    private List<CommentBean.ResultBean> lists;

    private String timeString;

    @Override
    protected int fillView() {
        return R.layout.activity_work_info;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        workInfoPresenter = new WorkInfoPresenter(this);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nrecycler.setLayoutManager(layoutManager);
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {

            id = intent.getIntExtra("news_id", -1);
            title = intent.getStringExtra("title");
            banner_url = intent.getStringExtra("url");
        }

        timeString = StringUtils.getTimeString();

        url = BASE_URL + "/note/" + id+ "?managerid=" + managerId;

        Map<String, String> map = new HashMap<String, String>();
        map.put("assetionkey", StringUtils.getBase64(RetrofitHelper.key + timeString));
        map.put("timestamp", timeString);

        if (id == -1) {
            wv.loadUrl(banner_url, map);
            if (title != null) {
                tv_title.setText(title);
            }
        } else {
            wv.loadUrl(url, map);
            if (title != null) {
                tv_title.setText(title);
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    workInfoPresenter.getComment(id);
                }
            }, 1000);
        }
    }

    private void initAdapterClike() {

        mAdapter.setonItemClickLitener(new PingLunAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    @OnClick({R.id.left, R.id.rl_comment, R.id.iv_commment, R.id.iv_collect, R.id.iv_praise, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.rl_comment:
                if (managerId == -1) {
                    startActivity(new Intent(this,LoginActivity.class));
                } else {
                    commentDialog = new CommentDialog(this);
                    commentDialog.show();
                    commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                        @Override
                        public void onCommit(EditText et, View v) {
                            String s = et.getText().toString();
                            if (TextUtils.isEmpty(s)) {
                                ToastUtils.show(WorkInfoActivity.this, "请先输入评论", Toast.LENGTH_SHORT);
                            }
                            workInfoPresenter.commitComment1(id, "", s, managerId);
                        }
                    });
                }

                break;
            case R.id.iv_commment:

                break;
            case R.id.iv_collect:

                break;
            case R.id.iv_praise:

                workInfoPresenter.thumb(id);
                break;
            case R.id.iv_share:
                String  shareUrl = BASE_URL + "/share/note/" + id + "?managerid=" + managerId+"&&"+"timestamp="+timeString+"&&"+"assetionkey="+StringUtils.getBase64(RetrofitHelper.key + timeString);
                UMWeb web = new UMWeb(shareUrl);
                web.setTitle("党建");//标题
                web.setThumb(new UMImage(this, R.mipmap.thumb));  //缩略图
                web.setDescription("实时发布党新闻和活动");//描述
                new ShareAction(this).withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
                break;
        }
    }


    @Override
    public void onGetDataNext(KongBean kongBean) {

    }

    @Override
    public void onGetCommentNext(CommentBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            rl_pinglun.setVisibility(View.VISIBLE);

            if (kongBean.result != null && kongBean.result.size() > 0) {
                lists = kongBean.result;
                mAdapter = new PingLunAdapter(lists, this);
                nrecycler.setAdapter(mAdapter);
                initAdapterClike();

                linearLayout_no_content.setVisibility(View.GONE);
                nrecycler.setVisibility(View.VISIBLE);

            } else {
                linearLayout_no_content.setVisibility(View.VISIBLE);
                nrecycler.setVisibility(View.GONE);
            }

        } else {
            ToastUtils.show(this, kongBean.result_msg, Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void onCommentNext(KongBean kongBean) {

        if (kongBean.result_code != null && kongBean.result_code.equals("200")) {
            commentDialog.dismiss();
            workInfoPresenter.getComment(id);

            ToastUtils.show(this, "提交成功", Toast.LENGTH_SHORT);

        } else {
            ToastUtils.show(this, "提交评论失败", Toast.LENGTH_SHORT);
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
            Toast.makeText(WorkInfoActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WorkInfoActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WorkInfoActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

}
