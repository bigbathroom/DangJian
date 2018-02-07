package com.fw.dangjian.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.fw.dangjian.MyApplication;
import com.fw.dangjian.R;
import com.fw.dangjian.adapter.ChannelAdapter;
import com.fw.dangjian.bean.Channel;
import com.fw.dangjian.listener.ItemDragHelperCallBack;
import com.fw.dangjian.listener.OnChannelDragListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fw.dangjian.bean.Channel.TYPE_MY_CHANNEL;

public class ChannelActivity extends AppCompatActivity implements OnChannelDragListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<Channel> mDatas = new ArrayList<>();
    private ChannelAdapter mAdapter;
    private final String[] titles = new String[]{"推荐", "视频", "热点", "社会", "娱乐", "科技", "汽车", "体育", "财经", "军事", "国际", "时尚", "游戏", "旅游", "历史", "探索", "美食", "育儿", "养生", "故事", "美文"};
    private ItemTouchHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);
        MyApplication.getInstance().addActivity(this);

        processLogic(savedInstanceState);

    }


    public void processLogic(Bundle savedInstanceState) {
        mAdapter = new ChannelAdapter(mDatas);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });

        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        mHelper = new ItemTouchHelper(callBack);
        mAdapter.setOnChannelDragListener(this);
        //attachRecyclerView
        mHelper.attachToRecyclerView(mRecyclerView);

    }


    public static void start(Context context, List<Channel> list) {
        start(context, list, -1);
    }

    public static void start(Context context, List<Channel> list, int requestCode) {
        Intent intent = new Intent(context, ChannelActivity.class);
        intent.putExtra("data", (Serializable) list);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
        }
    }


    @Override
    public void onItemMove(int starPos, int endPos) {
//        if (starPos < 0||endPos<0) return;
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {

    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {

    }

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {

//开始拖动
        Log.i("SS", "开始拖动");
        mHelper.startDrag(baseViewHolder);

    }


    @OnClick(R.id.icon_collapse)
    public void onClick(View v) {
        Iterator<Channel> iterator = mDatas.iterator();
        while (iterator.hasNext()) {
            Channel next = iterator.next();
            if (next.getItemType() != TYPE_MY_CHANNEL)
                iterator.remove();
        }
        Intent data = new Intent();
        data.putExtra("data", (Serializable) mDatas);
        setResult(RESULT_OK, data);
        finish();
    }
}
