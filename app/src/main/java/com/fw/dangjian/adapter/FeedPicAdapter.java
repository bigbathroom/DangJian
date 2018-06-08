package com.fw.dangjian.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fw.dangjian.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @beibei on 2017/5/22
 */

public class FeedPicAdapter extends RecyclerView.Adapter<FeedPicAdapter.ViewHolder> {


    private List<Bitmap> lists;
    private List<File> drr;
    private Context context;
    private onItemClickLitener monItemClickLitener;

    private Handler handler;

    public FeedPicAdapter(List<Bitmap> lists, List<File> drr, Context context, Handler handler) {
        this.lists = lists;
        this.context = context;
        this.drr = drr;
        this.handler = handler;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_feed_pic, parent, false);
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

        if (monItemClickLitener != null) {

            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monItemClickLitener.onItemClick(view, position);
                }
            });
        }

        holder.iv_feed_pic.setImageBitmap(lists.get(position));

        if (position == lists.size() - 1) {
            holder.rl_delete.setVisibility(View.GONE);
        } else {
            holder.rl_delete.setVisibility(View.VISIBLE);

            holder.rl_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lists.remove(position);
                    drr.remove(position);
                    notifyDataSetChanged();

//                    handler.sendMessage(handler.obtainMessage(10, position));

                }
            });

        }
        if (position == 6) {
            holder.iv_feed_pic.setVisibility(View.INVISIBLE);
            holder.iv_feed_pic.setEnabled(false);
        } else {
            holder.iv_feed_pic.setVisibility(View.VISIBLE);
            holder.iv_feed_pic.setEnabled(true);
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_feed_pic)
        ImageView iv_feed_pic;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.rl_delete)
        RelativeLayout rl_delete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}


