package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.QuizBean;
import com.fw.dangjian.bean.SubmitBean;
import com.fw.dangjian.mvpView.QuizMvpView;
import com.fw.dangjian.presenter.QuizPersenter;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;
import com.fw.dangjian.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity implements QuizMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_type)
    TextView tv_type;

    @BindView(R.id.tv_question_num)
    TextView tv_question_num;
    @BindView(R.id.tv_question)
    TextView tv_question;

    @BindView(R.id.rb_1)
    RadioButton rb_1;
    @BindView(R.id.rb_2)
    RadioButton rb_2;
    @BindView(R.id.rb_3)
    RadioButton rb_3;
    @BindView(R.id.rb_4)
    RadioButton rb_4;
    @BindView(R.id.rb_5)
    RadioButton rb_5;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.ll_cb)
    LinearLayout ll_cb;

    @BindView(R.id.CheckBox1)
    CheckBox CheckBox1;
    @BindView(R.id.CheckBox2)
    CheckBox CheckBox2;
    @BindView(R.id.CheckBox3)
    CheckBox CheckBox3;
    @BindView(R.id.CheckBox4)
    CheckBox CheckBox4;
    @BindView(R.id.CheckBox5)
    CheckBox CheckBox5;


    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_last)
    TextView tv_last;


    private QuizPersenter quizPersenter;
    private int squareId;


    List<String> rightAnswers = new ArrayList<>();
    private int count;
    int allCount = 0;
    private List<QuizBean.ResultBean.SubjectBean> subject;

    int managerId;
    private String titles;

    @Override
    protected int fillView() {
        return R.layout.activity_question;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);

        managerId = (int) SPUtils.get(this, ConstanceValue.LOGIN_TOKEN, -1);

        rb_1.setEnabled(false);
        rb_2.setEnabled(false);
        rb_3.setEnabled(false);
        rb_4.setEnabled(false);
        rb_5.setEnabled(false);
        CheckBox1.setEnabled(false);
        CheckBox2.setEnabled(false);
        CheckBox3.setEnabled(false);
        CheckBox4.setEnabled(false);
        CheckBox5.setEnabled(false);

        rg.setVisibility(View.GONE);
        ll_cb.setVisibility(View.GONE);


        Intent intent = getIntent();
        if (intent != null) {
            squareId = intent.getIntExtra("squareId", -1);
            titles = intent.getStringExtra("title");
            tv_title.setText(titles.substring(0,titles.indexOf("(")));
        }
        quizPersenter = new QuizPersenter(this);
    }

    @Override
    protected void initData() {
        quizPersenter.GetQuestion(squareId);
    }

    @OnClick({R.id.left, R.id.tv_last, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.tv_last:
                tv_last.setEnabled(false);
                tv_last.setBackgroundResource(R.drawable.bt_gray);
                if (allCount > 0) {
                            setChoiceData(allCount-2);
                            allCount = allCount - 1;
                }
                break;

            case R.id.tv_next:
                tv_next.setEnabled(false);
                tv_next.setBackgroundResource(R.drawable.bt_gray);

                if (count > allCount) {
                            setChoiceData(allCount);
                            allCount = allCount + 1;
                }

                break;
        }
    }

    @Override
    public void onGetDataNext(QuizBean actionBean) {
        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {

            count = actionBean.result.subject.size();
            subject = actionBean.result.subject;

            if (count > 0) {
                setChoiceData(allCount);
                allCount = allCount + 1;
            }
        } else {
            ToastUtils.show(this, actionBean.result_msg, Toast.LENGTH_SHORT);
        }

    }

    public void setChoiceData(int allCount) {

        if(allCount == 0){
            tv_last.setEnabled(false);
            tv_last.setBackgroundResource(R.drawable.bt_gray);
        }else if((allCount+1) == count){
            tv_next.setEnabled(false);
            tv_next.setBackgroundResource(R.drawable.bt_gray);
        }else{
            tv_last.setEnabled(true);
            tv_last.setBackgroundResource(R.drawable.bt_blue);
            tv_next.setEnabled(true);
            tv_next.setBackgroundResource(R.drawable.bt_blue);
        }

        rg.clearCheck();

        CheckBox1.setChecked(false);
        CheckBox2.setChecked(false);
        CheckBox3.setChecked(false);
        CheckBox4.setChecked(false);
        CheckBox5.setChecked(false);


        int pageNum = allCount + 1;
        tv_question_num.setText(pageNum + "/" + count);
        tv_question.setText(subject.get(allCount).subject_name);
        if (subject.get(allCount).subject_type == 1) {
            tv_type.setText("单选");
            rg.setVisibility(View.VISIBLE);
            ll_cb.setVisibility(View.GONE);

            if (subject.get(allCount).optionEntity.size() == 4) {
                rb_4.setVisibility(View.VISIBLE);
                rb_5.setVisibility(View.GONE);
                rb_1.setText("A " + subject.get(allCount).optionEntity.get(0).option_name);
                rb_2.setText("B " + subject.get(allCount).optionEntity.get(1).option_name);
                rb_3.setText("C " + subject.get(allCount).optionEntity.get(2).option_name);
                rb_4.setText("D " + subject.get(allCount).optionEntity.get(3).option_name);

            } else if (subject.get(allCount).optionEntity.size() == 3) {
                rb_4.setVisibility(View.GONE);
                rb_5.setVisibility(View.GONE);
                rb_1.setText("A " + subject.get(allCount).optionEntity.get(0).option_name);
                rb_2.setText("B " + subject.get(allCount).optionEntity.get(1).option_name);
                rb_3.setText("C " + subject.get(allCount).optionEntity.get(2).option_name);
            }else if (subject.get(allCount).optionEntity.size() == 5) {
                rb_4.setVisibility(View.VISIBLE);
                rb_5.setVisibility(View.VISIBLE);
                rb_1.setText("A " + subject.get(allCount).optionEntity.get(0).option_name);
                rb_2.setText("B " + subject.get(allCount).optionEntity.get(1).option_name);
                rb_3.setText("C " + subject.get(allCount).optionEntity.get(2).option_name);
                rb_4.setText("D " + subject.get(allCount).optionEntity.get(3).option_name);
                rb_5.setText("E " + subject.get(allCount).optionEntity.get(4).option_name);
            }

            for (int i = 1; i < subject.get(allCount).optionEntity.size() + 1; i++) {
                if (subject.get(allCount).optionEntity.get(i-1).isOk == 1) {
                    switch (i) {
                        case 1:
                            rb_1.setChecked(true);
                            break;
                        case 2:
                            rb_2.setChecked(true);
                            break;
                        case 3:
                            rb_3.setChecked(true);
                            break;
                        case 4:
                            rb_4.setChecked(true);
                            break;
                        case 5:
                            rb_5.setChecked(true);
                            break;
                    }
                }
            }

        } else {
            tv_type.setText("多选");
            rg.setVisibility(View.GONE);
            ll_cb.setVisibility(View.VISIBLE);
            if (subject.get(allCount).optionEntity.size() == 4) {
                CheckBox5.setVisibility(View.GONE);
                CheckBox4.setVisibility(View.VISIBLE);
                CheckBox1.setText("    A " + subject.get(allCount).optionEntity.get(0).option_name);
                CheckBox2.setText("    B " + subject.get(allCount).optionEntity.get(1).option_name);
                CheckBox3.setText("    C " + subject.get(allCount).optionEntity.get(2).option_name);
                CheckBox4.setText("    D " + subject.get(allCount).optionEntity.get(3).option_name);
            } else if (subject.get(allCount).optionEntity.size() == 3) {
                CheckBox4.setVisibility(View.GONE);
                CheckBox5.setVisibility(View.GONE);
                CheckBox1.setText("    A " + subject.get(allCount).optionEntity.get(0).option_name);
                CheckBox2.setText("    B " + subject.get(allCount).optionEntity.get(1).option_name);
                CheckBox3.setText("    C " + subject.get(allCount).optionEntity.get(2).option_name);
            } else if (subject.get(allCount).optionEntity.size() == 5) {
                CheckBox4.setVisibility(View.VISIBLE);
                CheckBox5.setVisibility(View.VISIBLE);
                CheckBox1.setText("    A " + subject.get(allCount).optionEntity.get(0).option_name);
                CheckBox2.setText("    B " + subject.get(allCount).optionEntity.get(1).option_name);
                CheckBox3.setText("    C " + subject.get(allCount).optionEntity.get(2).option_name);
                CheckBox4.setText("    D " + subject.get(allCount).optionEntity.get(3).option_name);
                CheckBox5.setText("    E " + subject.get(allCount).optionEntity.get(4).option_name);
            }

            for (int i = 1; i < subject.get(allCount).optionEntity.size() + 1; i++) {
                if (subject.get(allCount).optionEntity.get(i-1).isOk == 1) {
                    switch (i) {
                        case 1:
                            CheckBox1.setChecked(true);
                            break;
                        case 2:
                            CheckBox2.setChecked(true);
                            break;
                        case 3:
                            CheckBox3.setChecked(true);
                            break;
                        case 4:
                            CheckBox4.setChecked(true);
                            break;
                        case 5:
                            CheckBox5.setChecked(true);
                            break;
                    }
                }
            }
        }

    }

    @Override
    public void onSubmitDataNext(SubmitBean submitBean) {

    }
}
