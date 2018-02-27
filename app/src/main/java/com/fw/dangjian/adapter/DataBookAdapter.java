package com.fw.dangjian.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class DataBookAdapter extends RecyclerView.Adapter<DataBookAdapter.ViewHolder> {
    private RecyclerView mRecyclerView;
    private List<String> lists;
    private Context context;
    private onItemClickLitener monItemClickLitener;

    static View VIEW_FOOTER;
    static View VIEW_HEADER;

    //Type
    int TYPE_NORMAL = 1000;
    int TYPE_HEADER = 1001;
    public int TYPE_FOOTER = 1002;


    public DataBookAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new ViewHolder(VIEW_HEADER);
        } else {
            return new ViewHolder(getLayout(R.layout.item_book_data));
        }
    }

    public void setonItemClickLitener(onItemClickLitener monItemClickLitener) {
        this.monItemClickLitener = monItemClickLitener;
    }

    public interface onItemClickLitener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            if (position % 4 == 0) {
                holder.image.setBackgroundResource(R.mipmap.a);
                holder.show_title1.setVisibility(View.VISIBLE);
                holder.show_time1.setVisibility(View.VISIBLE);
                holder.show_title2.setVisibility(View.GONE);
                holder.show_time2.setVisibility(View.GONE);

                holder.show_title1.setText("80多个工地同步再键，一座新城池拔地而起，16万多家注册企业");
                holder.show_time1.setText("2018.02");
            } else if (position % 4 == 1) {
                holder.image.setBackgroundResource(R.mipmap.b);
                holder.show_title1.setVisibility(View.GONE);
                holder.show_time1.setVisibility(View.GONE);
                holder.show_title2.setVisibility(View.VISIBLE);
                holder.show_time2.setVisibility(View.VISIBLE);

                holder.show_title2.setText("80多个工地同步再键，一座新城池拔地而起，16万多家注册企业");
                holder.show_time2.setText("2018.02");
            } else if (position % 4 == 2) {
                holder.image.setBackgroundResource(R.mipmap.c);
                holder.show_title1.setVisibility(View.VISIBLE);
                holder.show_time1.setVisibility(View.VISIBLE);
                holder.show_title2.setVisibility(View.GONE);
                holder.show_time2.setVisibility(View.GONE);

                holder.show_title1.setText("80多个工地同步再键，一座新城池拔地而起，16万多家注册企业");
                holder.show_time1.setText("2018.02");
            } else if (position % 4 == 3) {
                holder.image.setBackgroundResource(R.mipmap.d);
                holder.show_title1.setVisibility(View.GONE);
                holder.show_time1.setVisibility(View.GONE);
                holder.show_title2.setVisibility(View.VISIBLE);
                holder.show_time2.setVisibility(View.VISIBLE);

                holder.show_title2.setText("80多个工地同步再键，一座新城池拔地而起，16万多家注册企业");
                holder.show_time2.setText("2018.02");
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


    }

    @Override
    public int getItemCount() {
     /*   int count = (data == null ? 0 : data.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;*/

        return 8;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
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
            if (!view.equals(VIEW_HEADER)&&!view.equals(VIEW_FOOTER)){
                ButterKnife.bind(this, view);
            }
        }
    }
}
