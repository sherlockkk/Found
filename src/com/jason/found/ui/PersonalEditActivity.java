package com.jason.found.ui;

import android.os.Bundle;

import com.jason.found.entity.Detail;
import com.jason.found.ui.base.BaseFragment;
import com.jason.found.ui.base.BaseHomeActivity;
import com.jason.found.utils.ActivityUtil;
@Deprecated
public class PersonalEditActivity extends BaseHomeActivity{

	@Override
	protected String getActionBarTitle() {
		// TODO Auto-generated method stub
		return "修改个人信息";
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
		return PersonalEditFragment.newInstance();
	}

	@Override
	protected void addActions() {
		// TODO Auto-generated method stub
		
	}

}
