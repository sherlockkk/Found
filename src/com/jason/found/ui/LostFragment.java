/**
 * 
 */
package com.jason.found.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.found.R;
import com.jason.found.adapter.QiangContentAdapter;
import com.jason.found.ui.base.BaseFragment;

/**
 * @author jason
 * @date 2015-8-12
 * 
 */
public class LostFragment extends BaseFragment implements OnPageChangeListener {

	private View contentView;
	private QiangContentAdapter mAdapter;
	private ViewPager mViewPager;

	/**
	 * 
	 */
	public static BaseFragment newInstance() {
		BaseFragment baseFragment = new LostFragment();
		return baseFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView = inflater.inflate(R.layout.activity_home_lost, null);
		mViewPager = (ViewPager) contentView.findViewById(R.id.viewpager_lost);
		mAdapter = new QiangContentAdapter(getActivity()
				.getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		return contentView;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
