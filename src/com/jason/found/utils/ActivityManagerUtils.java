package com.jason.found.utils;

import java.util.ArrayList;

import android.app.Activity;

/**
 * @author jason
 * @date 2015-8-13 TODO
 * 
 *       TODO Activity收集以及释放
 */

public class ActivityManagerUtils {

	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	private static ActivityManagerUtils activityManagerUtils;

	private ActivityManagerUtils() {

	}

	public static ActivityManagerUtils getInstance() {
		if (null == activityManagerUtils) {
			activityManagerUtils = new ActivityManagerUtils();
		}
		return activityManagerUtils;
	}

	public Activity getTopActivity() {
		return activityList.get(activityList.size() - 1);
	}

	public void addActivity(Activity ac) {
		activityList.add(ac);
	}

	public void removeAllActivity() {
		for (Activity ac : activityList) {
			if (null != ac) {
				if (!ac.isFinishing()) {
					ac.finish();
				}
				ac = null;
			}
		}
		activityList.clear();
	}
}
