package com.jason.found.ui.base;

import android.os.Bundle;
import cn.bmob.v3.Bmob;

import com.jason.found.utils.Constant;

/**
 * @author jason
 * @date 2015-8-13 TODO
 */

public abstract class BasePageActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		Bmob.initialize(this, Constant.BMOB_APP_ID);
		setLayoutView();
		init(bundle);
	}

	// ////
	private void init(Bundle bundle) {
		// TODO Auto-generated method stub
		findViews();
		setupViews(bundle);
		setListener();
		fetchData();
	}

	protected abstract void setLayoutView();

	protected abstract void findViews();

	protected abstract void setupViews(Bundle bundle);

	protected abstract void setListener();

	protected abstract void fetchData();

}
