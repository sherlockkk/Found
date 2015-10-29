package com.jason.found.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jason.found.ui.FoundContentFragment;

public class FoundContentAdapter extends SmartFragmentStatePagerAdapter {

	private static int NUM_ITEMS = 1;

	public FoundContentAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		if (this.getRegisteredFragment(position) != null) {
			return getRegisteredFragment(position);
		} else {
			// return
			// FavFragment.newInstance();//QiangContentFragment.newInstance(position);
			return FoundContentFragment.newInstance(position);
		}
		// return MenuContentFragment.newInstance("pager" + position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NUM_ITEMS;
	}

}
