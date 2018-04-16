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
import com.fw.dangjian.bean.BookBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class DataBookAdapter extends RecyclerView.Adapter<DataBookAdapter.ViewHolder> {
    private List<BookBean.ResultBean.PageInfoBean.ListBean> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;


    public DataBookAdapter(List<BookBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_data, parent, false);
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
        if (position % 4 == 0) {
            holder.image.setBackgroundResource(R.mipmap.a);
            holder.show_title1.setVisibility(View.VISIBLE);
            holder.show_time1.setVisibility(View.VISIBLE);
            holder.show_title2.setVisibility(View.GONE);
            holder.show_time2.setVisibility(View.GONE);

            holder.show_title1.setText(lists.get(position).post_title);
            holder.show_time1.setText(lists.get(position).post_date);
        } else if (position % 4 == 1) {
            holder.image.setBackgroundResource(R.mipmap.b);
            holder.show_title1.setVisibility(View.GONE);
            holder.show_time1.setVisibility(View.GONE);
            holder.show_title2.setVisibility(View.VISIBLE);
            holder.show_time2.setVisibility(View.VISIBLE);

            holder.show_title2.setText(lists.get(position).post_title);
            holder.show_time2.setText(lists.get(position).post_date);
        } else if (position % 4 == 2) {
            holder.image.setBackgroundResource(R.mipmap.c);
            holder.show_title1.setVisibility(View.VISIBLE);
            holder.show_time1.setVisibility(View.VISIBLE);
            holder.show_title2.setVisibility(View.GONE);
            holder.show_time2.setVisibility(View.GONE);

            holder.show_title1.setText(lists.get(position).post_title);
            holder.show_time1.setText(lists.get(position).post_date);
        } else if (position % 4 == 3) {
            holder.image.setBackgroundResource(R.mipmap.d);
            holder.show_title1.setVisibility(View.GONE);
            holder.show_time1.setVisibility(View.GONE);
            holder.show_title2.setVisibility(View.VISIBLE);
            holder.show_time2.setVisibility(View.VISIBLE);

            holder.show_title2.setText(lists.get(position).post_title);
            holder.show_time2.setText(lists.get(position).post_date);
        }
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
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.show_title1)
        TextView show_title1;
        @BindView(R.id.show_time1)
        TextView show_time1;
        @BindView(R.id.show_title2)
        TextView show_title2;
        @BindView(R.id.show_time2)
        TextView show_time2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
