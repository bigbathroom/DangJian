package com.fw.dangjian.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fw.dangjian.R;
import com.fw.dangjian.wheel.widget.views.AbstractWheelTextAdapter;
import com.fw.dangjian.wheel.widget.views.OnWheelChangedListener;
import com.fw.dangjian.wheel.widget.views.OnWheelScrollListener;
import com.fw.dangjian.wheel.widget.views.WheelView;

import java.util.ArrayList;

public class ChangeColumnDialog extends Dialog implements View.OnClickListener,OnWheelChangedListener {

	//省市区控件
	private WheelView wvProvince;

	private TextView btnSure;//确定按钮
	private TextView btnCancel;//取消按钮

	private Context context;//上下文对象

	private ArrayList<String> arrProvinces;


	private AddressTextAdapter provinceAdapter;

	//选中的省市区信息
	private String strProvince;

	//回调方法
	private OnAddressCListener onAddressCListener;

	//显示文字的字体大小
	private int maxsize = 20;
	private int minsize = 15;
	private LinearLayout out;

	public ChangeColumnDialog(Context context,ArrayList<String> arrProvinces) {
		super(context, R.style.ShareDialog);
		this.context = context;
		this.arrProvinces = arrProvinces;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_column);

		wvProvince = (WheelView) findViewById(R.id.wv_address_province);
		btnSure = (TextView) findViewById(R.id.tv_sure);
		btnCancel = (TextView) findViewById(R.id.tv_cancle);
		out = (LinearLayout) findViewById(R.id.ll_dialog_out);

		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		out.setOnClickListener(this);

		wvProvince.addChangingListener(this);


		provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
		wvProvince.setVisibleItems(3);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCyclic(false);//设置内容循环
		wvProvince.setCurrentItem(getProvinceItem(strProvince));


		wvProvince.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
				strProvince = currentText;
			}
		});

	}

	/**
	 * 初始化地点
	 *
	 * @param province
	 */
	public void setAddress(String province) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {

	}

	/**
	 * 返回省会索引
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrProvinces.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strProvince = province;
			return 0;
		}
		return provinceIndex;
	}


	private class AddressTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;
		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public interface OnAddressCListener {
		public void onClick(String province);
	}
	
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(20);
			} else {
				textvew.setTextSize(15);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tv_cancle:
				break;
			case R.id.tv_sure:
				if (onAddressCListener != null) {
					onAddressCListener.onClick(strProvince);
				}
				break;
			case R.id.ll_dialog_out:
				break;
		}
		dismiss();
	}
}