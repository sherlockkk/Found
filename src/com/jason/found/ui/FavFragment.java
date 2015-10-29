package com.jason.found.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jason.found.MyApplication;
import com.jason.found.adapter.AIContentAdapter;
import com.jason.found.adapter.BaseContentAdapter;
import com.jason.found.entity.Detail;
import com.jason.found.ui.base.BaseContentFragment;

public class FavFragment extends BaseContentFragment{

	public static FavFragment newInstance(){
		FavFragment fragment = new FavFragment();
		return fragment;
	}
	
	@Override
	public BaseContentAdapter<Detail> getAdapter() {
		// TODO Auto-generated method stub
		return new AIContentAdapter(mContext, mListItems);
	}

	@Override
	public void onListItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
//		MyApplication.getInstance().setCurrentQiangYu(mListItems.get(position-1));
		Intent intent = new Intent();
		intent.setClass(getActivity(), LostCommentActivity.class);
		intent.putExtra("data", mListItems.get(position-1));
		startActivity(intent);
	}
}
