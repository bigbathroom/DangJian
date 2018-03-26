package com.fw.dangjian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class BookDataAdapter extends BaseAdapter {

    private List<String> lists;
    private Context context;

    public BookDataAdapter(List<String> lists,Context context) {
        this.lists = lists;
        this.context = context;
    }


    @Override
    public int getCount() {

        return 8;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {
            convertView= LayoutInflater.from(this.context).inflate(R.layout.item_book_data, null);
            holder=new ViewHolder();

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

        return convertView;
    }

    private static class ViewHolder{
        ImageView image;
        TextView show_title1;
        TextView show_time1;
        TextView show_title2;
        TextView show_time2;
    }
}
