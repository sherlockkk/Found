package com.jason.found.entity;

import cn.bmob.v3.BmobObject;

/**
 * @author jason
 * @date 2015-8-10 TODO
 */

public class Comment_Lost extends BmobObject {

	public static final String TAG = "Comment_Lost";

	private User user;
	private String commentContent;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

}
