package com.fw.dangjian.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fw.dangjian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qhc on 2017/9/22.
 */
public abstract class ErrorDialog extends Dialog {

    public Context context;
    @BindView(R.id.tv_error)
    TextView tv_error;
    @BindView(R.id.sure)
    TextView sure;

    @OnClick(R.id.sure)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure:
                confirm();
                dismiss();
                break;
        }
    }

    public ErrorDialog(Context context, int themeResId) {
        super(context,themeResId);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_error);
        ButterKnife.bind(this);
    }

    public abstract void confirm();

}
