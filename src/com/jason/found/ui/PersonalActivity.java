package com.jason.found.ui;

import android.view.View;

import com.jason.found.R;
import com.jason.found.ui.PersonalFragment.IProgressControllor;
import com.jason.found.ui.base.BaseFragment;
import com.jason.found.ui.base.BaseHomeActivity;
import com.markupartist.android.widget.ActionBar.Action;

public class PersonalActivity extends BaseHomeActivity implements IProgressControllor{

	@Override
	protected String getActionBarTitle() {
		// TODO Auto-generated method stub
		return "个人中心";
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
		return PersonalFragment.newInstance();
	}

	@Override
	protected void addActions() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void showActionBarProgress(){
		actionBar.setProgressBarVisibility(View.VISIBLE);
	}
	
	@Override
	public void hideActionBarProgress(){
		actionBar.setProgressBarVisibility(View.GONE);
	}
}
