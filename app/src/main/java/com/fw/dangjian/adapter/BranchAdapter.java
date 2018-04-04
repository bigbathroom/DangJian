package com.fw.dangjian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.WaterBean;
import com.fw.dangjian.view.WaterFallActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {

    private List<WaterBean.ResultBean> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;
    private String activityName;
    private ArrayList<String> imageLists;

    public BranchAdapter(List<WaterBean.ResultBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_branch_photo, parent, false);
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

        imageLists = new ArrayList<>();
        imageLists.clear();

        activityName = lists.get(position).activityName;

        for (int i = 0;i<lists.get(position).imgAarray.size();i++){
            imageLists.add(lists.get(position).imgAarray.get(i).url);
        }

        holder.tv_title.setText(lists.get(position).activityName);

        GridePhotoAdapter pictureAdapter = new GridePhotoAdapter(lists.get(position).imgAarray, context);
        holder.gv.setAdapter(pictureAdapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, WaterFallActivity.class);
                intent.putExtra("title", activityName);
                intent.putStringArrayListExtra("photos", imageLists);
                context.startActivity(intent);
            }
        });

        holder.tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WaterFallActivity.class);
                intent.putExtra("title",activityName);
                intent.putStringArrayListExtra("photos", imageLists);
                context.startActivity(intent);
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
        RelativeLayout rlGoods;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_more)
        TextView tv_more;
        @BindView(R.id.gv)
        GridView gv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
