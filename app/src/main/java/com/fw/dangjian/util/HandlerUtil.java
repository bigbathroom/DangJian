package com.fw.dangjian.util;

import android.os.Handler;
import android.os.Message;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class HandlerUtil extends Handler {
    private XRecyclerView mNRecyclerView;

    public HandlerUtil(XRecyclerView mNRecyclerView) {

        this.mNRecyclerView = mNRecyclerView;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case 1:

                mNRecyclerView.loadMoreComplete();
                mNRecyclerView.refreshComplete();
                break;
            case 2:

                mNRecyclerView.refreshComplete();
                mNRecyclerView.loadMoreComplete();
                break;
            case 3:
                mNRecyclerView.refreshComplete();
                mNRecyclerView.loadMoreComplete();
                break;
        }
    }
}
