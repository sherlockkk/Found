package com.jason.found.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jason.found.R;
import com.jason.found.ui.base.BasePageActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

public class FavActivity extends BasePageActivity{

	private ActionBar actionbar;
	
	@Override
	protected void setLayoutView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_fav);
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		actionbar = (ActionBar)findViewById(R.id.actionbar_fav);
	}

	@Override
	protected void setupViews(Bundle bundle) {
		// TODO Auto-generated method stub
		actionbar.setTitle("我的收藏");
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setHomeAction(new Action() {
			
			@Override
			public void performAction(View view) {
				// TODO Auto-generated method stub
				finish();
			}
			
			@Override
			public int getDrawable() {
				// TODO Auto-generated method stub
				return R.drawable.logo;
			}
		});
		
		setInitialFragment();
	}

	private void setInitialFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.content_frame_fav, FavFragment.newInstance())
				.commit();
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
