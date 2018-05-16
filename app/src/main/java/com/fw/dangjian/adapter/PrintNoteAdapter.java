package com.fw.dangjian.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.PrintBean;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @贝贝 on 2017/5/23
 */

public class PrintNoteAdapter extends RecyclerView.Adapter<PrintNoteAdapter.ViewHolder> {

    private List<PrintBean> lists;
    private Context context;
    public Handler handler;
    private onItemClickLitener monItemClickLitener;
    public Map<Integer, Boolean> selectedMap;
    public  HashSet<PrintBean> printIdSet;


    public PrintNoteAdapter(List<PrintBean> lists, Context context, Handler handler,Map<Integer, Boolean> selectedMap,HashSet<PrintBean> printIdSet) {
        this.lists = lists;
        this.context = context;
        this.handler = handler;
        this.selectedMap = selectedMap;
        this.printIdSet = printIdSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
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
        holder.cb_single.setVisibility(View.VISIBLE);
        holder.tvTitle.setText(lists.get(position).post_title);

        if (lists.get(position).post_excerpt == null) {
            holder.tvTitle1.setVisibility(View.GONE);
        } else {
            holder.tvTitle1.setVisibility(View.VISIBLE);
            holder.tvTitle1.setText(lists.get(position).post_excerpt);
        }
        holder.tv_content.setText("\u3000\u3000" + lists.get(position).content);
        holder.tv_time.setText(lists.get(position).addtime);


        holder.cb_single.setChecked(selectedMap.get(position));

        holder.cb_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedMap.put(position, isChecked);

                // 判断是否有记录没被选中，以便修改全选CheckBox勾选状态
                if (selectedMap.containsValue(false)) {
                    handler.sendMessage(handler.obtainMessage(10, false));
                } else {
                    handler.sendMessage(handler.obtainMessage(10, true));
                }

                   // 保存记录Id
                if (selectedMap.get(position)) {
                    printIdSet.add(lists.get(position));
                    handler.sendMessage(handler.obtainMessage(11, printIdSet));
                } else {
                    printIdSet.remove(lists.get(position));
                    handler.sendMessage(handler.obtainMessage(11, printIdSet));
                }


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
        TextView tvTitle;
        @BindView(R.id.tv_title1)
        TextView tvTitle1;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.cb_single)
        CheckBox cb_single;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
