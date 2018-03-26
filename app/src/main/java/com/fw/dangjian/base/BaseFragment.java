package com.fw.dangjian.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.fw.dangjian.MainActivity;
import com.fw.dangjian.R;
import com.fw.dangjian.dialog.ErrorDialog;
import com.fw.dangjian.util.ConstanceValue;
import com.fw.dangjian.util.SPUtils;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment implements BaseMvpView{

    public static final String TAG = "DJ";
    public Activity act;
    public View view;
    public LayoutInflater layoutinflater;
    private ErrorDialog errorDialog;
    private int managerId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initFragment();
        view = fillView();
        ButterKnife.bind(this, view);


        initUi();
        initData();

        return view;
    }
    /**
     * 初始化fragment
     */
    public void initFragment() {
        act = getActivity();
        layoutinflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /**
     * 填充布局
     *
     * @return
     */
    protected abstract View fillView();
    /**
     * 初始化界面控件
     */
    protected abstract void initUi();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * startActivity
     *
     * @param clazz
     */
    public void jumpToActivity(Class clazz) {
        Intent intent = new Intent(act, clazz);
        getActivity().startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void jumpToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(act, clazz);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }


    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        if (act.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            InputMethodManager manager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (act.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(act.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //    加载失败网络异常框
    public void showErrorDialog(){
        errorDialog = new ErrorDialog(getActivity(), R.style.MyDarkDialog) {
            @Override
            public void confirm() {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        };
        errorDialog.show();
    }

    //    用户id
    public int getManagerId() {
        managerId = (int) SPUtils.get(act, ConstanceValue.LOGIN_TOKEN, -1);
        return managerId;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(errorDialog!=null){
            errorDialog.dismiss();
        }
    }

    @Override
    public void onGetDataCompleted() {

    }

    @Override
    public void onGetDataError(Throwable e) {
//        showErrorDialog();
    }
}
