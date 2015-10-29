package com.jason.found.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.jason.found.MyApplication;
import com.jason.found.R;
import com.jason.found.adapter.LostCommentAdapter;
import com.jason.found.db.DatabaseUtil;
import com.jason.found.entity.Comment_Lost;
import com.jason.found.entity.Detail;
import com.jason.found.entity.User;
import com.jason.found.sns.TencentShare;
import com.jason.found.sns.TencentShareEntity;
import com.jason.found.ui.base.BasePageActivity;
import com.jason.found.utils.ActivityUtil;
import com.jason.found.utils.Constant;
import com.jason.found.utils.LogUtils;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * @author jason
 * @date 2015-8-13 TODO
 */

public class LostCommentActivity extends BasePageActivity implements
		OnClickListener {

	private ActionBar actionbar;
	private ListView commentList;
	private TextView footer;

	private EditText commentContent;
	private Button commentCommit;

	private TextView userName;
	private TextView commentItemContent;
	private ImageView commentItemImage;

	private ImageView userLogo;
	private ImageView myFav;
	private TextView comment;
	private TextView share;
	private TextView love;
	private TextView hate;

	private Detail detail;
	private String commentEdit = "";

	private LostCommentAdapter mAdapter;

	private List<Comment_Lost> comment_Losts = new ArrayList<Comment_Lost>();

	private int pageNum;

	@Override
	protected void setLayoutView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_comment);
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		actionbar = (ActionBar) findViewById(R.id.actionbar_comment);
		commentList = (ListView) findViewById(R.id.comment_list);
		footer = (TextView) findViewById(R.id.loadmore);

		commentContent = (EditText) findViewById(R.id.comment_content);
		commentCommit = (Button) findViewById(R.id.comment_commit);

		userName = (TextView) findViewById(R.id.user_name);
		commentItemContent = (TextView) findViewById(R.id.content_text);
		commentItemImage = (ImageView) findViewById(R.id.content_image);

		userLogo = (ImageView) findViewById(R.id.user_logo);
		myFav = (ImageView) findViewById(R.id.item_action_fav);
		comment = (TextView) findViewById(R.id.item_action_comment);
		share = (TextView) findViewById(R.id.item_action_share);
		love = (TextView) findViewById(R.id.item_action_love);
		hate = (TextView) findViewById(R.id.item_action_hate);

	}

	@Override
	protected void setupViews(Bundle bundle) {
		// TODO Auto-generated method stub

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		detail = (Detail) getIntent().getSerializableExtra("data");// MyApplication.getInstance().getCurrentQiangYu();
		pageNum = 0;

		actionbar.setTitle("发表评论");
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

		mAdapter = new LostCommentAdapter(LostCommentActivity.this,
				comment_Losts);
		commentList.setAdapter(mAdapter);
		setListViewHeightBasedOnChildren(commentList);
		commentList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ActivityUtil.show(LostCommentActivity.this, position + 1 + "楼");
			}
		});
		commentList.setCacheColorHint(0);
		commentList.setScrollingCacheEnabled(false);
		commentList.setScrollContainer(false);
		commentList.setFastScrollEnabled(true);
		commentList.setSmoothScrollbarEnabled(true);

		initMoodView(detail);
	}

	private void initMoodView(Detail mood2) {
		// TODO Auto-generated method stub
		if (mood2 == null) {
			return;
		}
		userName.setText(detail.getAuthor().getUsername());
		commentItemContent.setText(detail.getContent());
		if (null == detail.getContentfigureurl()) {
			commentItemImage.setVisibility(View.GONE);
		} else {
			commentItemImage.setVisibility(View.VISIBLE);
			ImageLoader.getInstance().displayImage(
					detail.getContentfigureurl().getFileUrl(
							LostCommentActivity.this) == null ? "" : detail
							.getContentfigureurl().getFileUrl(
									LostCommentActivity.this),
					commentItemImage,
					MyApplication.getInstance().getOptions(
							R.drawable.bg_pic_loading),
					new SimpleImageLoadingListener() {

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// TODO Auto-generated method stub
							super.onLoadingComplete(imageUri, view, loadedImage);
							float[] cons = ActivityUtil.getBitmapConfiguration(
									loadedImage, commentItemImage, 1.0f);
							RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
									(int) cons[0], (int) cons[1]);
							layoutParams.addRule(RelativeLayout.BELOW,
									R.id.content_text);
							commentItemImage.setLayoutParams(layoutParams);
						}

					});
		}

		love.setText(detail.getLove() + "");
		if (detail.getMyLove()) {
			love.setTextColor(Color.parseColor("#D95555"));
		} else {
			love.setTextColor(Color.parseColor("#000000"));
		}
		hate.setText(detail.getHate() + "");
		if (detail.getMyFav()) {
			myFav.setImageResource(R.drawable.ic_action_fav_choose);
		} else {
			myFav.setImageResource(R.drawable.ic_action_fav_normal);
		}

		User user = detail.getAuthor();
		BmobFile avatar = user.getAvatar();
		if (null != avatar) {
			ImageLoader.getInstance().displayImage(
					avatar.getFileUrl(LostCommentActivity.this),
					userLogo,
					MyApplication.getInstance().getOptions(
							R.drawable.content_image_default),
					new SimpleImageLoadingListener() {

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// TODO Auto-generated method stub
							super.onLoadingComplete(imageUri, view, loadedImage);
							LogUtils.i(TAG, "load personal icon completed.");
						}

					});
		}
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		footer.setOnClickListener(this);
		commentCommit.setOnClickListener(this);

		userLogo.setOnClickListener(this);
		myFav.setOnClickListener(this);
		love.setOnClickListener(this);
		hate.setOnClickListener(this);
		share.setOnClickListener(this);
		comment.setOnClickListener(this);
	}

	@Override
	protected void fetchData() {
		// TODO Auto-generated method stub
		fetchComment();
	}

	private void fetchComment() {
		BmobQuery<Comment_Lost> query = new BmobQuery<Comment_Lost>();
		query.addWhereRelatedTo("relation", new BmobPointer(detail));
		query.include("user");
		query.order("createdAt");
		query.setLimit(Constant.NUMBERS_PER_PAGE);
		query.setSkip(Constant.NUMBERS_PER_PAGE * (pageNum++));
		query.findObjects(this, new FindListener<Comment_Lost>() {

			@Override
			public void onSuccess(List<Comment_Lost> data) {
				// TODO Auto-generated method stub
				LogUtils.i(TAG, "get comment success!" + data.size());
				if (data.size() != 0 && data.get(data.size() - 1) != null) {

					if (data.size() < Constant.NUMBERS_PER_PAGE) {
						ActivityUtil.show(mContext, "已加载完所有评论~");
						footer.setText("暂无更多评论~");
					}

					mAdapter.getDataList().addAll(data);
					mAdapter.notifyDataSetChanged();
					setListViewHeightBasedOnChildren(commentList);
					LogUtils.i(TAG, "refresh");
				} else {
					ActivityUtil.show(mContext, "暂无更多评论~");
					footer.setText("暂无更多评论~");
					pageNum--;
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				ActivityUtil.show(LostCommentActivity.this, "获取评论失败。请检查网络~");
				pageNum--;
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_logo:
			onClickUserLogo();
			break;
		case R.id.loadmore:
			onClickLoadMore();
			break;
		case R.id.comment_commit:
			onClickCommit();
			break;
		case R.id.item_action_fav:
			onClickFav(v);
			break;
		case R.id.item_action_love:
			onClickLove();
			break;
		case R.id.item_action_hate:
			onClickHate();
			break;
		case R.id.item_action_share:
			onClickShare();
			break;
		case R.id.item_action_comment:
			onClickComment();
			break;
		default:
			break;
		}
	}

	private void onClickUserLogo() {
		// TODO Auto-generated method stub
		// 跳转到个人信息界面
		// ============================================================//

		// ============================================================//

		User currentUser = BmobUser.getCurrentUser(this, User.class);
		if (currentUser != null) {// 已登录
			Intent intent = new Intent();
			intent.setClass(MyApplication.getInstance().getTopActivity(),
					PersonalActivity.class);
			mContext.startActivity(intent);
		} else {// 未登录
			ActivityUtil.show(this, "请先登录。");
			Intent intent = new Intent();
			intent.setClass(this, RegisterAndLoginActivity.class);
			startActivityForResult(intent, Constant.GO_SETTINGS);
		}
	}

	private void onClickLoadMore() {
		// TODO Auto-generated method stub
		fetchData();
	}

	private void onClickCommit() {
		// TODO Auto-generated method stub
		User currentUser = BmobUser.getCurrentUser(this, User.class);
		if (currentUser != null) {// 已登录
			commentEdit = commentContent.getText().toString().trim();
			if (TextUtils.isEmpty(commentEdit)) {
				ActivityUtil.show(this, "评论内容不能为空。");
				return;
			}
			// comment now
			publishComment(currentUser, commentEdit);
		} else {// 未登录
			ActivityUtil.show(this, "发表评论前请先登录。");
			Intent intent = new Intent();
			intent.setClass(this, RegisterAndLoginActivity.class);
			startActivityForResult(intent, Constant.PUBLISH_COMMENT);
		}

	}

	private void publishComment(User user, String content) {

		final Comment_Lost comment_Lost = new Comment_Lost();
		comment_Lost.setUser(user);
		comment_Lost.setCommentContent(content);
		comment_Lost.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				ActivityUtil.show(LostCommentActivity.this, "评论成功。");
				if (mAdapter.getDataList().size() < Constant.NUMBERS_PER_PAGE) {
					mAdapter.getDataList().add(comment_Lost);
					mAdapter.notifyDataSetChanged();
					setListViewHeightBasedOnChildren(commentList);
				}
				commentContent.setText("");
				hideSoftInput();

				// 将该评论与强语绑定到一起
				BmobRelation relation = new BmobRelation();
				relation.add(comment_Lost);
				detail.setRelation(relation);
				detail.update(mContext, new UpdateListener() {

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						LogUtils.i(TAG, "更新评论成功。");
						// fetchData();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						LogUtils.i(TAG, "更新评论失败。" + arg1);
					}
				});

			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				ActivityUtil.show(LostCommentActivity.this, "评论失败。请检查网络~");
			}
		});
	}

	private void onClickFav(View v) {
		// TODO Auto-generated method stub

		User user = BmobUser.getCurrentUser(this, User.class);
		if (user != null && user.getSessionToken() != null) {
			BmobRelation favRelaton = new BmobRelation();
			detail.setMyFav(!detail.getMyFav());
			if (detail.getMyFav()) {
				((ImageView) v)
						.setImageResource(R.drawable.ic_action_fav_choose);
				favRelaton.add(detail);
				ActivityUtil.show(mContext, "收藏成功。");
			} else {
				((ImageView) v)
						.setImageResource(R.drawable.ic_action_fav_normal);
				favRelaton.remove(detail);
				ActivityUtil.show(mContext, "取消收藏。");
			}

			user.setFavorite(favRelaton);
			user.update(this, new UpdateListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					LogUtils.i(TAG, "收藏成功。");
					ActivityUtil.show(LostCommentActivity.this, "收藏成功。");
					// try get fav to see if fav success
					// getMyFavourite();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					LogUtils.i(TAG, "收藏失败。请检查网络~");
					ActivityUtil.show(LostCommentActivity.this, "收藏失败。请检查网络~"
							+ arg0);
				}
			});
		} else {
			// 前往登录注册界面
			ActivityUtil.show(this, "收藏前请先登录。");
			Intent intent = new Intent();
			intent.setClass(this, RegisterAndLoginActivity.class);
			startActivityForResult(intent, Constant.SAVE_FAVOURITE);
		}

	}

	private void getMyFavourite() {
		User user = BmobUser.getCurrentUser(this, User.class);
		if (user != null) {
			BmobQuery<Detail> query = new BmobQuery<Detail>();
			query.addWhereRelatedTo("favorite", new BmobPointer(user));
			query.include("user");
			query.order("createdAt");
			query.setLimit(Constant.NUMBERS_PER_PAGE);
			query.findObjects(this, new FindListener<Detail>() {

				@Override
				public void onSuccess(List<Detail> data) {
					// TODO Auto-generated method stub
					LogUtils.i(TAG, "get fav success!" + data.size());
					ActivityUtil.show(LostCommentActivity.this, "fav size:"
							+ data.size());
				}

				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					ActivityUtil
							.show(LostCommentActivity.this, "获取收藏失败。请检查网络~");
				}
			});
		} else {
			// 前往登录注册界面
			ActivityUtil.show(this, "获取收藏前请先登录。");
			Intent intent = new Intent();
			intent.setClass(this, RegisterAndLoginActivity.class);
			startActivityForResult(intent, Constant.GET_FAVOURITE);
		}
	}

	boolean isFav = false;

	private void onClickLove() {
		// TODO Auto-generated method stub
		User user = BmobUser.getCurrentUser(this, User.class);
		if (user == null) {
			// 前往登录注册界面
			ActivityUtil.show(this, "请先登录。");
			Intent intent = new Intent();
			intent.setClass(this, RegisterAndLoginActivity.class);
			startActivity(intent);
			return;
		}
		if (detail.getMyLove()) {
			ActivityUtil.show(LostCommentActivity.this, "您已经赞过啦");
			return;
		}
		isFav = detail.getMyFav();
		if (isFav) {
			detail.setMyFav(false);
		}
		detail.setLove(detail.getLove() + 1);
		love.setTextColor(Color.parseColor("#D95555"));
		love.setText(detail.getLove() + "");
		detail.increment("love", 1);
		detail.update(mContext, new UpdateListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				detail.setMyLove(true);
				detail.setMyFav(isFav);
				DatabaseUtil.getInstance(mContext).insertFav(detail);

				ActivityUtil.show(mContext, "点赞成功~");
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void onClickHate() {
		// TODO Auto-generated method stub
		detail.setHate(detail.getHate() + 1);
		hate.setText(detail.getHate() + "");
		detail.increment("hate", 1);
		detail.update(mContext, new UpdateListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				ActivityUtil.show(mContext, "点踩成功~");
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void onClickShare() {
		// TODO Auto-generated method stub
		ActivityUtil.show(LostCommentActivity.this, "share to ...");
		final TencentShare tencentShare = new TencentShare(MyApplication
				.getInstance().getTopActivity(), getQQShareEntity(detail));
		tencentShare.shareToQQ();
	}

	private TencentShareEntity getQQShareEntity(Detail detail) {
		String title = "Found，让失物招领更简单";
		String comment = "Found，让失物招领更简单";
		String img = null;
		if (detail.getContentfigureurl() != null) {
			img = detail.getContentfigureurl().getFileUrl(
					LostCommentActivity.this);
		} else {
			img = "http://file.bmob.cn/M01/02/81/oYYBAFXQ0FSAQK0MAAAIL5UeSzs818.jpg";
		}
		String summary = detail.getContent();

		String targetUrl = "http://found520.bmob.cn";
		TencentShareEntity entity = new TencentShareEntity(title, img,
				targetUrl, summary, comment);
		return entity;
	}

	private void onClickComment() {
		// TODO Auto-generated method stub
		commentContent.requestFocus();

		InputMethodManager imm = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(commentContent, 0);
	}

	private void hideSoftInput() {
		InputMethodManager imm = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(commentContent.getWindowToken(), 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case Constant.PUBLISH_COMMENT:
				// 登录完成
				commentCommit.performClick();
				break;
			case Constant.SAVE_FAVOURITE:
				myFav.performClick();
				break;
			case Constant.GET_FAVOURITE:

				break;
			case Constant.GO_SETTINGS:
				userLogo.performClick();
				break;
			default:
				break;
			}
		}

	}

	/***
	 * 动态设置listview的高度 item 总布局必须是linearLayout
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))
				+ 15;
		listView.setLayoutParams(params);
	}

}
