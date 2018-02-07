package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;
import com.fw.dangjian.bean.BoxPageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {

private List<BoxPageBean.ResultBean.PageInfoBean.ListBean> lists;
private Context context;
private onItemClickLitener monItemClickLitener;

public BoxAdapter(List<BoxPageBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box, parent, false);
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
        Glide.with(context).load(lists.get(position).cover_url).into(holder.iv_goods);
        holder.tv_title.setText(lists.get(position).post_title);
        holder.tv_time.setText(lists.get(position).post_date);

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
        return lists.size();
    }

static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.iv_goods)
    ImageView iv_goods;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_lookcount)
    TextView tv_lookcount;
    @BindView(R.id.tv_likecount)
    TextView tv_likecount;

    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
