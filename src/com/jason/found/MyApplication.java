package com.jason.found;

import java.io.File;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import cn.bmob.v3.BmobUser;

import com.jason.found.entity.Detail;
import com.jason.found.entity.Found;
import com.jason.found.entity.User;
import com.jason.found.utils.ActivityManagerUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.umeng.fb.push.FeedbackPush;

/**
 * @author jason
 * @date 2015-8-10 TODO
 */

public class MyApplication extends Application {

	public static String URL = "";

	public static String TAG;

	private static MyApplication myApplication = null;

	private Detail currentQiangYu = null;

	private Found currentFound = null;

	public static MyApplication getInstance() {
		return myApplication;
	}

	public User getCurrentUser() {
		User user = BmobUser.getCurrentUser(myApplication, User.class);
		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TAG = this.getClass().getSimpleName();
		// 由于Application类本身已经单例，所以直接按以下处理即可。
		myApplication = this;
		initImageLoader();
		FeedbackPush.getInstance(this).init(true);
	}

	public Detail getCurrentQiangYu() {
		return currentQiangYu;
	}

	public void setCurrentQiangYu(Detail currentQiangYu) {
		this.currentQiangYu = currentQiangYu;
	}

	public Found getCurrentFound() {
		return currentFound;
	}

	public void setCurrentFound(Found currentFound) {
		this.currentFound = currentFound;
	}

	public void addActivity(Activity ac) {
		ActivityManagerUtils.getInstance().addActivity(ac);
	}

	public void exit() {
		ActivityManagerUtils.getInstance().removeAllActivity();
	}

	public Activity getTopActivity() {
		return ActivityManagerUtils.getInstance().getTopActivity();
	}

	/**
	 * 初始化imageLoader
	 */
	public void initImageLoader() {
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCache(new LruMemoryCache(5 * 1024 * 1024))
				.memoryCacheSize(10 * 1024 * 1024)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
				.build();
		ImageLoader.getInstance().init(config);
	}

	public DisplayImageOptions getOptions(int drawableId) {
		return new DisplayImageOptions.Builder().showImageOnLoading(drawableId)
				.showImageForEmptyUri(drawableId).showImageOnFail(drawableId)
				.resetViewBeforeLoading(true).cacheInMemory(true)
				.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

}
