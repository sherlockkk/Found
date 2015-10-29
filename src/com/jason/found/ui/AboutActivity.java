package com.jason.found.ui;


import com.jason.found.R;
import com.jason.found.ui.base.BaseFragment;
import com.jason.found.ui.base.BaseHomeActivity;

public class AboutActivity extends BaseHomeActivity{

	@Override
	protected String getActionBarTitle() {
		// TODO Auto-generated method stub
		return getString(R.string.about_title);
	}

	@Override
	protected boolean isHomeAsUpEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void onHomeActionClick() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	protected BaseFragment getFragment() {
		// TODO Auto-generated method stub
		return AboutFragment.newInstance();
	}

	@Override
	protected void addActions() {
		// TODO Auto-generated method stub
		
	}

	
}
