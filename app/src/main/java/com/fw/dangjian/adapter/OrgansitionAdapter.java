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
import com.fw.dangjian.bean.OrganisationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class OrgansitionAdapter extends RecyclerView.Adapter<OrgansitionAdapter.ViewHolder> {

    private List<OrganisationBean.ResultBean.ListBean> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;

    public OrgansitionAdapter(List<OrganisationBean.ResultBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ittem_organ, parent, false);
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
        //委员-0     中共党员-1       预备党员-2      发展对象-3       团员-4       群众-5   其它为空
        if (lists.get(position).rudangzhibu != null) {
            if (lists.get(position).rudangzhibu.equals("0")) {
//                Glide.with(context).load(R.mipmap.wei).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.wei);
                holder.tv_position.setText("委员");
            } else if (lists.get(position).rudangzhibu.equals("1")) {
//                Glide.with(context).load(R.mipmap.dang).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.dang);
                holder.tv_position.setText("中共党员");
            } else if (lists.get(position).rudangzhibu.equals("2")) {
//                Glide.with(context).load(R.mipmap.dang).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.dang);
                holder.tv_position.setText("预备党员");
            } else if (lists.get(position).rudangzhibu.equals("3")) {
//                Glide.with(context).load(R.mipmap.fazhangduixiang).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.fazhangduixiang);
                holder.tv_position.setText("发展对象");
            } else if (lists.get(position).rudangzhibu.equals("4")) {
                holder.iv.setBackgroundResource(R.mipmap.tuan);
                holder.tv_position.setText("团员");
            } else if (lists.get(position).rudangzhibu.equals("6")) {
//                Glide.with(context).load(R.mipmap.shuji).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.shuji);
                holder.tv_position.setText("书记");
            } else {
//                Glide.with(context).load(R.mipmap.publics).into(holder.iv);
                holder.iv.setBackgroundResource(R.mipmap.publics);
                holder.tv_position.setText("群众");
            }
        } else {
//                Glide.with(context).load(R.mipmap.publics).into(holder.iv);
            holder.iv.setBackgroundResource(R.mipmap.publics);
            holder.tv_position.setText("群众");

        }


        holder.tv_name.setText(lists.get(position).name);


        if (monItemClickLitener != null)

        {
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
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_position)
        TextView tv_position;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
