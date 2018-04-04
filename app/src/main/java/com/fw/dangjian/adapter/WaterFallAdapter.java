package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    private List<String> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;
    private List<Integer> heightList;//装产出的随机数

    public WaterFallAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context = context;

        heightList = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            int height = new Random().nextInt(200) + 330;//[100,300)的随机数
            heightList.add(height);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waterfall, parent, false);
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
        Glide.with(context).load(lists.get(position)).into(holder.imageView);

        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height=heightList.get(position);
        holder.imageView.setLayoutParams(params);

      /*  if (position % 2 == 0) {
            holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));
        } else {
            holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350));
        }*/

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
        @BindView(R.id.imageView1)
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
