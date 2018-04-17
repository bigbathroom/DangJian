package com.fw.dangjian.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fw.dangjian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/12.
 */

public class BookDialog extends Dialog{

    @BindView(R.id.tv_commit)
    TextView tv_commit;//提交
    @BindView(R.id.et_comment)
    EditText et_comment;//评论内容

    private Context context;
    private OnCommitListener listener;

    public BookDialog(Context context) {
        this(context, R.style.inputDialog);
        this.context = context;
    }

    public BookDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_dialog_layout);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        //设置显示对话框时的返回键的监听
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    BookDialog.this.cancel();
                return false;
            }
        });

    }

    public void setOnCommitListener(OnCommitListener listener) {
        this.listener = listener;
    }

    public interface OnCommitListener {

        void onCommit(EditText et, View v);//提交数据

    }

    @OnClick({ R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (null != listener) {
                    listener.onCommit(et_comment, view);
                }
                break;
        }
    }

}
