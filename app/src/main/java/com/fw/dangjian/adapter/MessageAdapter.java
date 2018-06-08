package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.MessageBean;
import com.fw.dangjian.util.ToastUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageBean.ResultBean.PageInfoBean.ListBean> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;

    public MessageAdapter(List<MessageBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_title.setText(lists.get(position).post_title);
        holder.tv_name.setText(lists.get(position).name);
        holder.tv_content.setText(lists.get(position).post_excerpt);
        holder.tv_time.setText(lists.get(position).post_date);

        if(lists.get(position).post_column == 3){
            holder.tv_source.setText("首页——活动新闻");
        }else if(lists.get(position).post_column == 4){
            holder.tv_source.setText("首页——党风廉洁");
        }else if(lists.get(position).post_column == 5){
            holder.tv_source.setText("首页——两学一做");
        }else if(lists.get(position).post_column == 10){
            holder.tv_source.setText("学习——十九大");
        }else if(lists.get(position).post_column == 11){
            holder.tv_source.setText("学习——两学一做");
        }else if(lists.get(position).post_column == 12){
            holder.tv_source.setText("学习——上级文件");
        }else if(lists.get(position).post_column == 13){
            holder.tv_source.setText("首页——要闻");
        }


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort(context, "id"+position);
            }
        });


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
        @BindView(R.id.swip_layout)
        SwipeMenuLayout swip_layout;
        @BindView(R.id.content)
        RelativeLayout rlGoods;
        @BindView(R.id.tv_from)
        TextView tv_source;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_message)
        TextView tv_content;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.btnDelete)
        Button btnDelete;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
