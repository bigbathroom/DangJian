package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.MeetBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class DataBookAdapter1 extends RecyclerView.Adapter<DataBookAdapter1.ViewHolder> {
    private List<MeetBean.ResultBean.PageInfoBean.ListBean> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;


    public DataBookAdapter1(List<MeetBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_data1, parent, false);
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
            holder.show_name.setText("主持人："+lists.get(position).meeting_author);
            holder.show_title.setText(lists.get(position).post_excerpt);
            holder.show_time.setText(lists.get(position).meeting_date_gmt);

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
        @BindView(R.id.show_name)
        TextView show_name;
        @BindView(R.id.show_title)
        TextView show_title;
        @BindView(R.id.show_time)
        TextView show_time;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
