package com.fw.dangjian.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.KongBean;
import com.fw.dangjian.dialog.CommentDialog;
import com.fw.dangjian.mvpView.WorkInfoMvpView;
import com.fw.dangjian.presenter.WorkInfoPresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkInfoActivity extends BaseActivity implements WorkInfoMvpView{

    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_total_title)
    TextView tv_total_title;
    @BindView(R.id.rv_touxiang)
    RoundedImageView rv_touxiang;
    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_care)
    TextView tv_care;
    @BindView(R.id.wv)
    WebView wv;
    @BindView(R.id.rl_comment)
    RelativeLayout rl_comment;
    @BindView(R.id.iv_commment)
    ImageView iv_commment;
    @BindView(R.id.iv_collect)
    ImageView iv_collect;
    @BindView(R.id.iv_praise)
    ImageView iv_praise;
    private int id;
    private WorkInfoPresenter workInfoPresenter;
    private CommentDialog commentDialog;
    private int managerId;
    private String url;
    private String url1;

    @Override
    protected int fillView() {
        return R.layout.activity_work_info;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("工作信息");
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);
        workInfoPresenter = new WorkInfoPresenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null){

            id = intent.getIntExtra("news_id",-1);
            url1 = intent.getStringExtra("url");

        }

        url = "http://112.126.73.158:8087/langur/api/note/"+id+"?managerid="+managerId;

        if(id == -1){
            wv.loadUrl(url1);
        }else{
            wv.loadUrl(url);
        }

    }

    @OnClick({R.id.left,R.id.tv_care,R.id.rl_comment,R.id.iv_commment,R.id.iv_collect,R.id.iv_praise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_care:

                break;
            case R.id.rl_comment:
                commentDialog = new CommentDialog(this);
                commentDialog.show();
                commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
                    @Override
                    public void onCommit(EditText et, View v) {
                        String s = et.getText().toString();
                        if (TextUtils.isEmpty(s)){
                            ToastUtils.show(WorkInfoActivity.this,"请先输入评论", Toast.LENGTH_SHORT);
                        }

                        if (managerId == -1){
                            workInfoPresenter.commitComment(id,"游客",s);
                        }else{
                            workInfoPresenter.commitComment1(id,"游客",s,managerId);
                        }
                    }
                });
                break;
            case R.id.iv_commment:

                break;
            case R.id.iv_collect:

                break;
            case R.id.iv_praise:

                workInfoPresenter.thumb(id);
                break;
        }
    }

    @Override
    public void onGetDataNext(KongBean kongBean) {

    }

    @Override
    public void onCommentNext(KongBean kongBean) {

        if(kongBean.result_code!=null && kongBean.result_code.equals("200")){
            commentDialog.dismiss();
//            wv.loadUrl(url);
            ToastUtils.show(this,"提交成功", Toast.LENGTH_SHORT);

        }else{
           ToastUtils.show(this,"提交评论失败",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onThumbNext(KongBean kongBean) {
        if(kongBean.result_code!=null && kongBean.result_code.equals("200")){
            iv_praise.setImageResource(R.mipmap.praise01);
//            wv.loadUrl(url);
        }else{
            ToastUtils.show(this,"点赞失败",Toast.LENGTH_SHORT);
        }
    }
}
