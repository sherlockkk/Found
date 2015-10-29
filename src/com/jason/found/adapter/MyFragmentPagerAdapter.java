package com.jason.found.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragmentsList;
	private List<String> titleList;
	Context context;

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fragmentsList = fragmentsList;
	}

	public MyFragmentPagerAdapter(FragmentManager fm,
			ArrayList<Fragment> fragments, List<String> titleList) {
		super(fm);
		this.fragmentsList = fragments;
		this.titleList = titleList;
	}

	@Override
	public int getCount() {
		return fragmentsList.size();
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragmentsList.get(arg0);
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		SpannableStringBuilder ssb = new SpannableStringBuilder(
				titleList.get(position));
		// Drawable myDrawable = context.getResources().getDrawable(
		// R.drawable.umeng_fb_logo);
		// myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(),
		// myDrawable.getIntrinsicHeight());
		// ImageSpan span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
		ForegroundColorSpan fcs = new ForegroundColorSpan(
				Color.parseColor("#D95555"));
		// ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ssb.setSpan(new RelativeSizeSpan(1.0f), 1, ssb.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return ssb;
	}
}
