package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.ActionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ViewHolder> {

private List<ActionBean.ResultBean.ListBean> lists;
private Context context;
private onItemClickLitener monItemClickLitener;

public ActionAdapter(List<ActionBean.ResultBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action, parent, false);
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
//        Glide.with(context).load(lists.get(position).mainImage).error(R.mipmap.baodan_defalut).into(holder.ivGoods);
        holder.tv_title.setText(lists.get(position).square_name);
        holder.tv_organization.setText(lists.get(position).square_region);
        holder.tv_name.setText(lists.get(position).square_author);
        holder.tv_time.setText("学时:"+lists.get(position).square_time+"分钟");

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
    LinearLayout rlGoods;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_organization)
    TextView tv_organization;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_time)
    TextView tv_time;

    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
