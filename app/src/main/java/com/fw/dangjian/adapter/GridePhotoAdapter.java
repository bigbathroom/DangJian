package com.fw.dangjian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fw.dangjian.R;
import com.fw.dangjian.bean.WaterBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class GridePhotoAdapter  extends BaseAdapter {
    private Context context;

    private List<WaterBean.ResultBean.ImgAarrayBean> images;

    public GridePhotoAdapter(List<WaterBean.ResultBean.ImgAarrayBean> images, Context context) {
        super();
        this.context = context;
        this.images = images;

    }

    @Override
    public int getCount() {

        if (null != images) {
            return images.size();
        } else {
            return 0;
        }


    }

    @Override
    public Object getItem(int position) {

        return images.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.picture_item, null);

            // 初始化组件
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_picture);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(images.get(position).url).into(viewHolder.image);

        return convertView;
    }

    class ViewHolder {
        public ImageView image;
    }

}
