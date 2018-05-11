package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.ScoreBean;
import com.fw.dangjian.mvpView.ScoreMvpView;
import com.fw.dangjian.presenter.ScorePresenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class ScoreActivity extends BaseActivity implements ScoreMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_question)
    TextView tv_question;
    @BindView(R.id.total_score)
    TextView total_score;
    @BindView(R.id.pecrcent)
    TextView pecrcent;
    @BindView(R.id.total_question)
    TextView total_question;
    @BindView(R.id.wrong_question)
    TextView wrong_question;
    @BindView(R.id.right_question)
    TextView right_question;
    @BindView(R.id.test_again)
    TextView test_again;


    int managerId;
    private ScorePresenter scorePresenter;
    private int testId;
    private String square_name;


    @Override
    protected int fillView() {
        return R.layout.activity_score;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        tv_title.setText("我的成绩");
        testId = getIntent().getIntExtra("testId", -1);

        scorePresenter = new ScorePresenter(this);
        scorePresenter.GetResult(managerId,testId);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onGetDataNext(ScoreBean scoreBean) {
        if (scoreBean.result_code != null && scoreBean.result_code.equals("200")) {
            int totleCount = scoreBean.result.totleCount;
            int count = scoreBean.result.count;
            int wrong = totleCount - count;
            square_name = scoreBean.result.square_name;
            NumberFormat nt = NumberFormat.getPercentInstance();
            //设置百分数精确度2即保留两位小数
            nt.setMinimumFractionDigits(0);
            float result = (float)count/totleCount*100;
            tv_question.setText(square_name);

            pecrcent.setText((int)result+"%");
            total_score.setText(scoreBean.result.score+"");
            total_question.setText(scoreBean.result.totleCount+"");
            right_question.setText(scoreBean.result.count+"");
            wrong_question.setText(wrong+"");
        } else {
            Toast.makeText(this, scoreBean.result_msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.left,R.id.test_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.test_again:
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("squareId",testId);
                intent.putExtra("title", square_name);
                startActivity(intent);
                finish();
        }
    }
}
