package com.fw.dangjian.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * Created by Administrator on 2017-10-4.
 */

public class CountDownUtil{


    private static CountDownTimer countDownTimer;

    public static void clearCountDown() {
        try {
            if(countDownTimer != null) {
                countDownTimer.cancel();
            }
        } catch (Exception ex) {

        }

    }
    public static CountDownTimer startCount(final Context context, int mins, final TextView tvTime) {
        CountDownTimer timer = new CountDownTimer(mins*60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished % 60000) / 1000));
            }
            @Override
            public void onFinish() {
                tvTime.setText("00:00");
            }
        };
        timer.start();
        countDownTimer = timer;
        return timer;
    }


    public static void cancle(CountDownTimer timer) {
        timer.cancel();
    }


    /*public static CountDownTimer startCount(int mins, TextView tvTime, ImageView ivTime) {
        CountDownTimer timer = new CountDownTimer(mins*60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(String.format("%02d", millisUntilFinished / 60000) + ":" + String.format("%02d", (millisUntilFinished % 60000) / 1000));
                if (millisUntilFinished < 60000 && millisUntilFinished > 59888) {

                    Resources resource = tvTime.getContext().getResources();
                    ColorStateList csl = resource.getColorStateList(R.color.colorWarmPink);
                    if (csl != null) {
                        tvTime.setTextColor(csl);
                    }
                    ivTime.setImageResource(R.drawable.timer_red);
                }
            }
            @Override
            public void onFinish() {
                tvTime.setText("00:00");
            }
        };
        timer.start();
        return timer;
    }

    public static CountDownTimer startCountAndProcess(long millis, TextView audioTime, ProgressBar progressBar) {
        CountDownTimer timer = new CountDownTimer(millis, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                String second = (millisUntilFinished % 60000) / 1000 + "";
                if (second.length() == 1) {
                    second = "0" + second;
                }
                audioTime.setText(String.format("%02d", (millisUntilFinished / 60000)) + ":" + second);
                //long progresss = Long.valueOf(100 - (millisUntilFinished * 100 / audioT)).intValue();
                progressBar.setProgress(Long.valueOf(100 - (millisUntilFinished * 100 / millis)).intValue());
            }

            @Override
            public void onFinish() {
                audioTime.setText("00:00");
                progressBar.setProgress(100);
                //强行结束测试
            }
        };
        timer.start();
        return timer;
    }

    public static CountDownTimer startCountAndProcess(long millis, ProgressBar progressBar) {
        CountDownTimer timer = new CountDownTimer(millis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                String second = (millisUntilFinished % 60000) / 1000 + "";
                if (second.length() == 1) {
                    second = "0" + second;
                }
                //long progresss = Long.valueOf(100 - (millisUntilFinished * 100 / audioT)).intValue();
                progressBar.setProgress(Long.valueOf(100 - (millisUntilFinished * 100 / millis)).intValue());
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(100);
                //强行结束测试
            }
        };
        timer.start();
        return timer;
    }

    public static void cancle(CountDownTimer timer) {
        timer.cancel();
    }



    public static CountDownTimer startTimePrompt(int mini, TextView timePrompt) {
        timePrompt.setVisibility(View.GONE);

        CountDownTimer timer = new CountDownTimer(mini*1000, mini*1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timePrompt.setText("已用" + mini + "秒，请合理安排时间");
                timePrompt.setVisibility(View.VISIBLE);
            }
        };
        return timer;
    }


    public static CountDownTimer startTimePrompt2(int mini, TextView timePrompt) {
        timePrompt.setVisibility(View.GONE);
        CountDownTimer timer = new CountDownTimer(mini*1000, mini*1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timePrompt.setText("已用" + mini + "秒，建议您选\"我不知道\"");
                timePrompt.setVisibility(View.VISIBLE);
            }
        };
        return timer;
    }


    public static void cancle(CountDownTimer timer, TextView timePrompt) {
        timer.cancel();
        timePrompt.setVisibility(View.GONE);
    }
*/
}
