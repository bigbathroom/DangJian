package com.fw.dangjian.view;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.dangjian.MyListView;
import com.fw.dangjian.R;
import com.fw.dangjian.adapter.BookDataAdapter;
import com.fw.dangjian.adapter.DataBookAdapter;
import com.fw.dangjian.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ManageFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    MyListView nrecycler;
    private DataBookAdapter mAdapter;
    private ArrayList<String> lists;
    ImageView iv;
    BookDataAdapter  adapter;
    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.activity_data_book, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("大事记");
        lists = new ArrayList<>();

    /*    nrecycler.setLayoutManager(new GridLayoutManager(getActivity(),1));

        mAdapter = new DataBookAdapter(lists, getActivity());
        nrecycler.setAdapter(mAdapter);

        mAdapter.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.book_head,null));
        mAdapter.setonItemClickLitener(new DataBookAdapter.onItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WorkInfoActivity.class);
                startActivity(intent);
            }
        });*/

      /*  //header
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.book_head, null);
        //可以添加多个HeaderView
        nrecycler.addHeaderView(headerView);*/
        adapter = new BookDataAdapter(lists, getActivity());
        nrecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), WorkInfoActivity.class);
        startActivity(intent);
    }
}