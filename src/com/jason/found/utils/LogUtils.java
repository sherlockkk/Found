package com.jason.found.utils;

import android.util.Log;

import com.jason.found.BuildConfig;

/**
 * @author jason
 * @date 2015-8-13 TODO
 * 
 *       TODO Log工具类，设置开关，防止发布版本时log信息泄露
 */

public class LogUtils {

	public static void v(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.v(tag, msg);
		}

	}

	public static void d(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}

	}

	public static void i(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.i(tag, msg);
		}

	}

	public static void w(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, msg);
		}

	}

	public static void e(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, msg);
		}
	}

}
