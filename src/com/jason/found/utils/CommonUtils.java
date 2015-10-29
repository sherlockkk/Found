package com.jason.found.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * @author jason TODO
 */

public class CommonUtils {

	/**
	 * 
	 * @param context
	 * @param assetsPah
	 *            :"fonts/xxx.ttf"
	 * @return usage:textView.setTypeface(typeface);
	 */
	public static Typeface getTypeface(Context context, String assetsPah) {
		Typeface typeface = Typeface.createFromAsset(context.getAssets(),
				assetsPah);
		return typeface;
	}
}
