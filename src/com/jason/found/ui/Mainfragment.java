package com.jason.found.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jason.found.R;
import com.jason.found.adapter.MyFragmentPagerAdapter;
import com.jason.found.adapter.QiangContentAdapter;
import com.jason.found.ui.base.BaseFragment;
import com.jason.found.utils.LogUtils;

/**
 * @author jason
 * @date 2015-8-12
 */

public class Mainfragment extends BaseFragment implements OnPageChangeListener {

	private View contentView;
	private ViewPager mViewPager;
	private QiangContentAdapter mAdapter;
	private List<View> viewList;
	private List<String> titleList;
	private ArrayList<Fragment> fragmentsList;
	Fragment lostFragment;
	Fragment foundFragment;

	public static BaseFragment newInstance() {
		BaseFragment fragment = new Mainfragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 设置title
		titleList = new ArrayList<String>();
		titleList.add("失物");
		titleList.add("招领");

		contentView = inflater.inflate(R.layout.fragment_main, null);
		InitViewPager(contentView);
		return contentView;
	}

	private void InitViewPager(View parentView) {
		mViewPager = (ViewPager) contentView.findViewById(R.id.viewpager_fm);
		fragmentsList = new ArrayList<Fragment>();
		// 设置title
		titleList = new ArrayList<String>();
		titleList.add("失物");
		titleList.add("招领");

		lostFragment = new LostFragment();
		foundFragment = new FoundFragment();

		fragmentsList.add(lostFragment);
		fragmentsList.add(foundFragment);

		MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(
				getChildFragmentManager(), fragmentsList, titleList);
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setCurrentItem(0);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), arg0 + "", Toast.LENGTH_SHORT).show();
		LogUtils.i(TAG, arg0 + "--->");
	}

	public void setCurrentPage(int targetIndex) {
		// mViewPager.setCurrentItem(targetIndex, false);
	}

}
