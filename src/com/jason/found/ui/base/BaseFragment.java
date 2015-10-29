package com.jason.found.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jason.found.utils.Constant;
import com.jason.found.utils.Sputil;
import com.umeng.analytics.MobclickAgent;

/**
 * @author jason
 * @date 2015-8-13 TODO
 */

public abstract class BaseFragment extends Fragment {
	public static String TAG;
	protected Context mContext;
	protected Sputil sputil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getSimpleName();
		mContext = getActivity();
		if (null == sputil) {
			sputil = new Sputil(mContext, Constant.PRE_NAME);
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart(TAG);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd(TAG);
	}
}
