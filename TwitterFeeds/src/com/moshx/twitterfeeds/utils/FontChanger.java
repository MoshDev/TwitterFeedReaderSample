package com.moshx.twitterfeeds.utils;

import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Mosh Ersan
 * 
 *         There is two ways to set the TextView font (typeface)</br> <b>1-
 *         using view tag</b></br> <b>2- using font name</b></br> </br> </br> #
 *         best practice to define font names (tags) in strings.xml</br> </br>
 *         Sample using case 1:</br> </br> refere to
 *         "font_changer_sample_type_1.xml" in layout directory</br> </br> after
 *         that you can use</br> </br> {@link FontChanger}.
 *         {@link #setActivityFont(Activity)}</br> </br> </br> Sample using case
 *         2:</br> </br> <code>
 * TextView txtView1 = (TextView) findViewById(R.id.textView1);</br>
 * FontChanger.setViewFont(txtView1,getString(R.string.sample_font_1));</br>
 * </code> </br> {@link FontChanger}.{@link #setViewFont(View, String)}</br>
 *         </br>
 * 
 *         </br> <b> PLEASE NOTE ALL FONTS MUST BE SAVED INTO
 *         <code>"assets/fonts/"</code> DIRECTORY </b>
 * 
 * 
 * 
 * 
 */

public class FontChanger {

	private static HashMap<String, Typeface> typefacesMap = new HashMap<String, Typeface>();

	private static Typeface loadTypeface(Context context, String name,
			boolean throwErrorIfNotFound) {

		if (typefacesMap.containsKey(name)) {
			return typefacesMap.get(name);
		} else {
			try {

				Typeface typeface = Typeface.createFromAsset(
						context.getAssets(), "fonts/" + name);
				typefacesMap.put(name, typeface);
				return typeface;

			} catch (Exception e) {
				if (throwErrorIfNotFound) {
					throw new IllegalArgumentException(e);
				} else {
					return null;
				}
			}
		}
	}

	/**
	 * 
	 * Will go through viewGroup children, and look for TextViews only, if
	 * TextView has tag, and this tag can be loaded as TypeFace name, it will
	 * set the TextView font as its Tag value
	 * 
	 * 
	 * @param viewGroup
	 */

	public static void setViewGroupFont(ViewGroup viewGroup) {

		if (viewGroup == null || viewGroup.getChildCount() == 0) {
			return;
		}
		boolean throwErrorIfNotFound = false;
		Context context = viewGroup.getContext();

		int childCount = viewGroup.getChildCount();
		View v;
		for (int i = 0; i < childCount; i++) {
			v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup) {
				setViewGroupFont((ViewGroup) v);
			} else if (v instanceof TextView) {
				TextView textView = (TextView) v;
				Object tagObject = textView.getTag();
				if (tagObject != null) {
					String s = tagObject.toString();

					Typeface typeface = loadTypeface(context, s,
							throwErrorIfNotFound);
					if (typeface == null) {
						continue;
					}
					textView.setTypeface(typeface);
				}
			}
		}

	}

	/**
	 * if View type is not TextView, or View tag is null, and not font name,
	 * this method has nothing to do
	 * 
	 * @param view
	 */

	public static void setViewFont(View view) {

		if (view == null || !(view instanceof TextView)) {
			return;
		}

		Object tag = view.getTag();
		if (tag == null) {
			return;
		}
		Context context = view.getContext();

		Typeface typeface = loadTypeface(context, tag.toString(), false);
		if (typeface == null) {
			return;
		}
		((TextView) view).setTypeface(typeface);

	}

	public static void setViewFont(View view, String fontName) {

		if (view == null || !(view instanceof TextView)
				|| TextUtils.isEmpty(fontName)) {
			return;
		}

		Context context = view.getContext();

		Typeface typeface = loadTypeface(context, fontName, true);
		if (typeface == null) {
			return;
		}
		((TextView) view).setTypeface(typeface);

	}

	/**
	 * Neither ViewGroup or FontName can be null,
	 * 
	 * 
	 * @param viewGroup
	 * @param fontName
	 * 
	 * @throws IllegalArgumentException
	 *             in case font not found;
	 */

	public static void setViewGroupFont(ViewGroup viewGroup, String fontName) {

		if (viewGroup == null || viewGroup.getChildCount() == 0
				|| TextUtils.isEmpty(fontName)) {
			return;
		}
		boolean throwErrorIfNotFound = true;
		Context context = viewGroup.getContext();

		Typeface typeface = loadTypeface(context, fontName,
				throwErrorIfNotFound);

		int childCount = viewGroup.getChildCount();
		View v;
		for (int i = 0; i < childCount; i++) {
			v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup) {
				setViewGroupFont((ViewGroup) v, fontName);
			} else if (v instanceof TextView) {
				TextView textView = (TextView) v;
				textView.setTypeface(typeface);
			}
		}

	}

	/**
	 * this method will go through all activity views, and look for TextViews.
	 * 
	 * 
	 * @param activity
	 * @param fontName
	 * 
	 * @throws IllegalArgumentException
	 *             in case font not found;
	 */

	public static void setActivityFont(Activity activity, String fontName) {

		if (activity == null || activity.getWindow().getDecorView() == null
				|| TextUtils.isEmpty(fontName)) {
			return;
		}

		setViewGroupFont((ViewGroup) activity.getWindow().getDecorView(),
				fontName);

	}

	public static void setDialogFont(Dialog dialog, String fontName) {
		if (dialog == null || dialog.getWindow().getDecorView() == null
				|| TextUtils.isEmpty(fontName)) {
			return;
		}

		setViewGroupFont((ViewGroup) dialog.getWindow().getDecorView(),
				fontName);
	}

	public static void setActivityFont(Activity activity) {

		if (activity == null || activity.getWindow().getDecorView() == null) {
			return;
		}

		setViewGroupFont((ViewGroup) activity.getWindow().getDecorView());

	}

	public static void setPaintFont(Context context, Paint paint,
			String fontName) {

		if (paint == null || TextUtils.isEmpty(fontName)) {
			return;
		}

		Typeface typeface = loadTypeface(context, fontName, true);
		paint.setTypeface(typeface);

	}

	public static void setToastFont(Toast toast, String fontName) {

		if (toast == null || TextUtils.isEmpty(fontName)) {
			return;
		}

		setViewGroupFont((ViewGroup) toast.getView(), fontName);

	}

	public static void setActionBarTitleFont(Activity context, String fontName) {

		int actionBarTitleId;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			actionBarTitleId = context.getResources().getIdentifier(
					"abs__action_bar_title", "id", context.getPackageName());
		} else {
			actionBarTitleId = Resources.getSystem().getIdentifier(
					"action_bar_title", "id", "android");
		}

		if (actionBarTitleId > 0) {
			View v = context.findViewById(actionBarTitleId);
			setViewFont(v, fontName);
		}

	}

}
