package com.jason.found.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.found.R;
import com.jason.found.entity.Comment_Found;
import com.jason.found.utils.LogUtils;

public class FoundCommentAdapter extends BaseContentAdapter<Comment_Found> {

	public FoundCommentAdapter(Context context, List<Comment_Found> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getConvertView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.comment_item, null);
			viewHolder.userName = (TextView) convertView
					.findViewById(R.id.userName_comment);
			viewHolder.commentContent = (TextView) convertView
					.findViewById(R.id.content_comment);
			viewHolder.index = (TextView) convertView
					.findViewById(R.id.index_comment);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final Comment_Found comment_Found = dataList.get(position);
		if (comment_Found.getUser() != null) {
			viewHolder.userName.setText(comment_Found.getUser().getUsername());
			LogUtils.i("FoundCommentActivity", "NAME:"
					+ comment_Found.getUser().getUsername());
		} else {
			viewHolder.userName.setText("墙友");
		}
		viewHolder.index.setText((position + 1) + "楼");
		viewHolder.commentContent.setText(comment_Found.getCommentContent());

		return convertView;
	}

	public static class ViewHolder {
		public TextView userName;
		public TextView commentContent;
		public TextView index;
	}
}
