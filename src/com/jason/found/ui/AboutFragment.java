package com.jason.found.ui;

import com.jason.found.R;
import com.jason.found.ui.base.BaseHomeFragment;
import com.jason.found.utils.ActivityUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AboutFragment extends BaseHomeFragment{
	private TextView versionName;
	public static AboutFragment newInstance(){
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}
	
	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.fragment_ourinfo;
	}

	@Override
	protected void findViews(View view) {
		// TODO Auto-generated method stub
		versionName = (TextView)view.findViewById(R.id.version_name);
		
	}

	@Override
	protected void setupViews(Bundle bundle) {
		// TODO Auto-generated method stub
		versionName.setText(ActivityUtil.getVersionName(mContext));
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fetchData() {
		// TODO Auto-generated method stub
		
	}


}
