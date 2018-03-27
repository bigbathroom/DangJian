package com.fw.dangjian.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.dangjian.R;
import com.fw.dangjian.base.BaseActivity;
import com.fw.dangjian.bean.Answer;
import com.fw.dangjian.bean.QuizBean;
import com.fw.dangjian.bean.SubmitBean;
import com.fw.dangjian.dialog.ResultDialog;
import com.fw.dangjian.mvpView.QuizMvpView;
import com.fw.dangjian.presenter.QuizPersenter;
import com.fw.dangjian.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QuizActivity extends BaseActivity implements QuizMvpView {

    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
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
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.iv_wrong)
    ImageView iv_wrong;

    private QuizPersenter quizPersenter;
    private int squareId;

    String choiceId = "";
    private int count;
    int allCount = 0;
    private List<QuizBean.ResultBean.SubjectBean> subject;
    private List<Answer> answerList;
    private ArrayList<String> cbId;
    private ResultDialog resultDialog;
    private CountDownTimer timer;

    @Override
    protected int fillView() {
        return R.layout.activity_quiz;
    }

    @Override
    protected void initUi() {
        left.setVisibility(View.VISIBLE);
        tv_title.setText("在线知识竞答");

        answerList = new ArrayList<>();
        cbId = new ArrayList<>();

        rg.setVisibility(View.GONE);
        ll_cb.setVisibility(View.GONE);
        iv_wrong.setVisibility(View.GONE);
        iv_right.setVisibility(View.GONE);
        tv_next.setEnabled(false);


        Intent intent = getIntent();
        if(intent != null){
            squareId = intent.getIntExtra("squareId", -1);
        }
        quizPersenter = new QuizPersenter(this);

    }

    @Override
    protected void initData() {
        quizPersenter.GetQuestion(squareId);
    }

    @OnClick({R.id.left, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4,R.id.CheckBox1,R.id.CheckBox2,R.id.CheckBox3,R.id.CheckBox4,R.id.CheckBox5,R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                finish();
                break;
            case R.id.rb_1:
                choiceId = "a";
                setEnable();
                rb_2.setChecked(false);
                rb_3.setChecked(false);
                rb_4.setChecked(false);
                break;
            case R.id.rb_2:
                choiceId = "b";
                setEnable();
                rb_1.setChecked(false);
                rb_3.setChecked(false);
                rb_4.setChecked(false);
                break;
            case R.id.rb_3:
                choiceId = "c";
                setEnable();
                rb_1.setChecked(false);
                rb_2.setChecked(false);
                rb_4.setChecked(false);
                break;
            case R.id.rb_4:
                choiceId = "d";
                setEnable();
                rb_1.setChecked(false);
                rb_2.setChecked(false);
                rb_3.setChecked(false);
                break;

            case R.id.CheckBox1:
                setEnable1();
                break;
            case R.id.CheckBox2:
                setEnable1();
                break;
            case R.id.CheckBox3:
                setEnable1();

                break;
            case R.id.CheckBox4:
                setEnable1();

                break;
            case R.id.CheckBox5:
                setEnable1();

                break;

            case R.id.tv_next:
                tv_next.setEnabled(false);
                rb_1.setEnabled(false);
                rb_2.setEnabled(false);
                rb_3.setEnabled(false);
                rb_4.setEnabled(false);
                CheckBox1.setEnabled(false);
                CheckBox2.setEnabled(false);
                CheckBox3.setEnabled(false);
                CheckBox4.setEnabled(false);
                CheckBox5.setEnabled(false);

//                把答案和题号装成对象添加到集合中
                cbId.clear();
                if(CheckBox1.isChecked()){
                    cbId.add("a");
                }
                if(CheckBox2.isChecked()){
                    cbId.add("b");
                }
                if(CheckBox3.isChecked()){
                    cbId.add("c");
                }
                if(CheckBox4.isChecked()){
                    cbId.add("d");
                }
                if(CheckBox5.isChecked()){
                    cbId.add("e");
                }

                Answer answerBean= new Answer();
                List<String> option_opt = new ArrayList<>();

                if(subject.get(allCount-1).subject_type == 1){
                    option_opt.add(choiceId);
                }else{
                    for(int i = 0;i<cbId.size();i++){
                        option_opt.add(cbId.get(i));
                    }
                }

                answerBean.setSubject_id(subject.get(allCount-1).id);
                answerBean.setOption_opt(option_opt);
                answerList.add(answerBean);


                if(subject.get(allCount-1).subject_type == 1){
                    if (choiceId .equals("")) {
                        iv_right.setVisibility(View.VISIBLE);
                    } else {
                        iv_wrong.setVisibility(View.VISIBLE);
                    }
                }else{
//                    多选判断对错

                }


                if (count > allCount) {
                    //    刷新题目页面
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rb_1.setEnabled(true);
                            rb_2.setEnabled(true);
                            rb_3.setEnabled(true);
                            rb_4.setEnabled(true);
                            CheckBox1.setEnabled(true);
                            CheckBox2.setEnabled(true);
                            CheckBox3.setEnabled(true);
                            CheckBox4.setEnabled(true);
                            CheckBox5.setEnabled(true);
                            rb_1.setChecked(false);
                            rb_2.setChecked(false);
                            rb_3.setChecked(false);
                            rb_4.setChecked(false);
                            CheckBox1.setChecked(false);
                            CheckBox2.setChecked(false);
                            CheckBox3.setChecked(false);
                            CheckBox4.setChecked(false);
                            CheckBox5.setChecked(false);
                            iv_wrong.setVisibility(View.GONE);
                            iv_right.setVisibility(View.GONE);

                            setChoiceData(allCount);
                            allCount = allCount + 1;
                        }
                    }, 1500);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            submitChoice();
                        }
                    },500);
                }

                break;
        }
    }

    private void setEnable() {
        tv_next.setEnabled(true);
    }
    private void setEnable1() {
        if (CheckBox1.isChecked() == true || CheckBox2.isChecked() == true || CheckBox3.isChecked() == true || CheckBox4.isChecked() == true || CheckBox5.isChecked() == true) {
            tv_next.setEnabled(true);
        }
    }


    private void submitChoice() {

        String answer = answerList.toString();
        quizPersenter.submitAnswer(squareId,answer);
    }


    @Override
    public void onGetDataNext(QuizBean actionBean) {

        if (actionBean.result_code != null && actionBean.result_code.equals("200")) {
            int time = actionBean.result.square_time;
            startCount(time);
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


    @Override
    public void onSubmitDataNext(SubmitBean submitBean) {

        if(submitBean.result_code != null && submitBean.result_code.equals("200")){

            resultDialog = new ResultDialog(this, R.style.MyDarkDialog,"","") {
                @Override
                public void confirm() {
                   dismiss();
                    QuizActivity.this.finish();
                }
            };
            resultDialog.show();

        }else{
            ToastUtils.show(this, submitBean.result_msg, Toast.LENGTH_SHORT);
        }
    }



    public void setChoiceData(int allCount) {
        int pageNum = allCount+1;
        tv_question_num.setText( pageNum + "/" + count);
        tv_question.setText(subject.get(allCount).subject_name);
        if(subject.get(allCount).subject_type == 1){
            tv_type.setText("单选");
            rg.setVisibility(View.VISIBLE);
            rb_1.setText("A " + subject.get(allCount).optionEntity.get(0).option_name);
            rb_2.setText("B " + subject.get(allCount).optionEntity.get(1).option_name);
            rb_3.setText("C " + subject.get(allCount).optionEntity.get(2).option_name);
            rb_4.setText("D " + subject.get(allCount).optionEntity.get(3).option_name);

        }else{
            tv_type.setText("多选");
            ll_cb.setVisibility(View.VISIBLE);
            CheckBox1.setText("A " + subject.get(allCount).optionEntity.get(0).option_name);
            CheckBox2.setText("B " + subject.get(allCount).optionEntity.get(1).option_name);
            CheckBox3.setText("C " + subject.get(allCount).optionEntity.get(2).option_name);
            CheckBox4.setText("D " + subject.get(allCount).optionEntity.get(3).option_name);

            if(subject.get(0).optionEntity.size()>4){
                CheckBox5.setVisibility(View.VISIBLE);
                CheckBox5.setText("E " + subject.get(allCount).optionEntity.get(4).option_name);
            }else{
                CheckBox5.setVisibility(View.GONE);
            }
        }
    }

    public void startCount(int mins) {
        timer = new CountDownTimer(mins*60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished % 60000) / 1000));
            }
            @Override
            public void onFinish() {
                tv_time.setText("00:00");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        submitChoice();
                    }
                },500);

            }
        };
        timer.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null) {
            timer.cancel();
        }
    }
}
