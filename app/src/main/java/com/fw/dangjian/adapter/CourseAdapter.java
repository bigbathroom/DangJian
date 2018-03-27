package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

private List<String> lists;
private Context context;
private onItemClickLitener monItemClickLitener;

public CourseAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
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
        /*Glide.with(context).load(lists.get(position).cover_url).into(holder.ivGoods);
        holder.tvGoodsTitle.setText(lists.get(position).post_title);
        holder.tv_time.setText(String.valueOf(lists.get(position).post_date));*/

        if (monItemClickLitener != null) {
            holder.rlGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monItemClickLitener.onItemClick(holder.itemView, holder.getPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
//        return lists.size();
        return  10;
    }


static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_state)
    TextView tv_state;


    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
