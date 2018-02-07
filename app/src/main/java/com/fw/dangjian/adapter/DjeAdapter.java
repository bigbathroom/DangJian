package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;
import com.fw.dangjian.bean.DjeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class DjeAdapter extends RecyclerView.Adapter<DjeAdapter.ViewHolder> {

private List<DjeBean.ResultBean.PageInfoBean.ListBean> lists;
private Context context;
private onItemClickLitener monItemClickLitener;

public DjeAdapter(List<DjeBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dje, parent, false);
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
        Glide.with(context).load(lists.get(position).cover_url).into(holder.ivGoods);
        holder.tv_title.setText(lists.get(position).post_title);
        holder.tv_source.setText(lists.get(position).post_source);
        holder.tv_title1.setText(lists.get(position).post_source+":");
        holder.tv_content.setText(lists.get(position).post_excerpt);
        holder.tv_time.setText(lists.get(position).post_date);

        holder.iv_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"哈哈哈",Toast.LENGTH_SHORT).show();
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
    @BindView(R.id.rl_goods)
    LinearLayout rlGoods;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_comment)
    TextView tv_comment;
    @BindView(R.id.iv_collect)
    ImageView iv_collect;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.iv_praise)
    ImageView iv_praise;
    @BindView(R.id.tv_praise)
    TextView tv_praise;

    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
}
