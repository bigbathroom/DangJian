package com.fw.dangjian.popwin;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.R;


/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class CameraPopu extends PopupWindow {
    public PopupWindow popupWindow=null;
    public Context mContext;
    public  CameraPopu (Context context, View.OnClickListener itemLister){
        this.mContext = context;
        View view = View.inflate(mContext, R.layout.layout_popu_camera, null);
        popupWindow=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextView camera = (TextView) view.findViewById(R.id.camera);
        TextView photo = (TextView) view.findViewById(R.id.photo);
        TextView cancle = (TextView) view.findViewById(R.id.cancle);
        RelativeLayout rl_pop = (RelativeLayout) view.findViewById(R.id.rl_pop);
        camera.setOnClickListener(itemLister);
        photo.setOnClickListener(itemLister);
        cancle.setOnClickListener(itemLister);
        rl_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        this.setContentView(view);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.ActionSheetDialogAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //设置popupWindow,当点击popupWindow外面的时候可以消失
//        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);

    }

}
