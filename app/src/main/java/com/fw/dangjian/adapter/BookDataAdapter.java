package com.fw.dangjian.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.bean.BookBean;
import com.fw.dangjian.view.WorkInfoActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class BookDataAdapter extends BaseAdapter {

    private List<BookBean.ResultBean.PageInfoBean.ListBean> lists;
    private Context context;

    public BookDataAdapter(List<BookBean.ResultBean.PageInfoBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }


    @Override
    public int getCount() {

        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {
            convertView= LayoutInflater.from(this.context).inflate(R.layout.item_book_data, null);
            holder=new ViewHolder();
            holder.rl_goods= (RelativeLayout) convertView.findViewById(R.id.rl_goods);
            holder.image= (ImageView) convertView.findViewById(R.id.image);
            holder.show_title1= (TextView) convertView.findViewById(R.id.show_title1);
            holder.show_time1= (TextView) convertView.findViewById(R.id.show_time1);
            holder.show_title2= (TextView) convertView.findViewById(R.id.show_title2);
            holder.show_time2= (TextView) convertView.findViewById(R.id.show_time2);

            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }

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

        holder.rl_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkInfoActivity.class);
               intent.putExtra("news_id",lists.get(position).id);
                intent.putExtra("title","大事记");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ViewHolder{
        ImageView image;
        TextView show_title1;
        TextView show_time1;
        TextView show_title2;
        TextView show_time2;
        RelativeLayout rl_goods;
    }
}
