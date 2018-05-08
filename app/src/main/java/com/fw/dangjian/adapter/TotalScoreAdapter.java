package com.fw.dangjian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.TotalScoreBean;
import com.fw.dangjian.view.QuizActivity;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class TotalScoreAdapter extends RecyclerView.Adapter<TotalScoreAdapter.ViewHolder> {

private List<TotalScoreBean.ResultBean> lists;
private Context context;
private onItemClickLitener monItemClickLitener;
    private int id;
    private String times;

    public TotalScoreAdapter(List<TotalScoreBean.ResultBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
        }

public void setonItemClickLitener(onItemClickLitener monItemClickLitener) {
        this.monItemClickLitener = monItemClickLitener;
        }

public interface onItemClickLitener {
    void onItemClick(View view, int position);
}

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int totleCount = lists.get(position).totleCount;
        int count = lists.get(position).count;
        int wrong = totleCount - count;

        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(0);
        float result = (float)count/totleCount*100;
        holder.tv_title.setText(lists.get(position).square_name);
        holder.tv_time.setText(lists.get(position).test_time);
        holder.pecrcent.setText((int)result+"%");
        holder.total_score.setText(lists.get(position).score+"");
        holder.total_question.setText(lists.get(position).totleCount+"");
        holder.wrong_question.setText(wrong+"");
        holder.right_question.setText(lists.get(position).count+"");

        id = lists.get(position).id;
        times = lists.get(position).times;

        holder.test_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("squareId", id);
                intent.putExtra("times", times);
                context.startActivity(intent);
            }
        });

        if (monItemClickLitener != null) {

            holder.ll_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monItemClickLitener.onItemClick(holder.itemView, holder.getPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.pecrcent)
    TextView pecrcent;
    @BindView(R.id.total_score)
    TextView total_score;
    @BindView(R.id.total_question)
    TextView total_question;
    @BindView(R.id.wrong_question)
    TextView wrong_question;
    @BindView(R.id.right_question)
    TextView right_question;
    @BindView(R.id.test_again)
    TextView test_again;
    @BindView(R.id.tv_time)
    TextView tv_time;
    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
