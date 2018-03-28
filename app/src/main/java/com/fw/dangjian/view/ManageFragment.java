package com.fw.dangjian.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fw.dangjian.MyListView;
import com.fw.dangjian.R;
import com.fw.dangjian.adapter.BookDataAdapter;
import com.fw.dangjian.base.BaseFragment;
import com.fw.dangjian.bean.BookBean;
import com.fw.dangjian.mvpView.BookMvpView;
import com.fw.dangjian.presenter.BookPresenter;

import java.util.List;

import butterknife.BindView;

public class ManageFragment extends BaseFragment implements BookMvpView,AdapterView.OnItemClickListener{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerview)
    MyListView nrecycler;
    @BindView(R.id.rl_lv)
    RelativeLayout rl_lv;
    @BindView(R.id.ll_no_content)
    LinearLayout ll_no_content;

    BookDataAdapter  adapter;
    private BookPresenter bookPresenter;
    private List<BookBean.ResultBean.PageInfoBean.ListBean> lists;

    @Override
    protected View fillView() {
        return layoutinflater.inflate(R.layout.activity_data_book, null);
    }

    @Override
    protected void initUi() {
        tv_title.setText("大事记");
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


        bookPresenter = new BookPresenter();
        bookPresenter.getBookPage(1,this);

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
    }

    @Override
    public void onGetDataNext(BookBean bookBean) {
        if (bookBean.result_code != null && bookBean.result_code.equals("200")){
            if(bookBean.result.pageInfo.list.size()>0){
                rl_lv.setVisibility(View.VISIBLE);
                ll_no_content.setVisibility(View.GONE);
                lists = bookBean.result.pageInfo.list;
                adapter = new BookDataAdapter(lists, getActivity());
                nrecycler.setAdapter(adapter);
            }else{
                rl_lv.setVisibility(View.GONE);
                ll_no_content.setVisibility(View.VISIBLE);
            }
        }
    }
}