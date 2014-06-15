package com.moshx.twitterfeeds.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.moshx.twitterfeeds.R;
import com.moshx.twitterfeeds.network.AbstractRequest;
import com.moshx.twitterfeeds.network.AbstractRequest.RequestType;

public class DialogsManager {

	public static void showDialog(Activity context, int title, int msg, int icon) {

		String t = context.getString(title);
		String m = context.getString(msg);

		showDialog(context, t, m, icon);

	}

	public static void showDialog(Activity activity, String title, String msg,
			int icon) {

		if (activity.isFinishing()) {
			return;
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setIcon(icon);
		builder.setNegativeButton(R.string.ok, null);
		Dialog dialog = builder.create();
		dialog.show();

		FontChanger.setDialogFont(dialog,
				activity.getString(R.string.font_default));
	}

	public static void showNoInternetConnection(Activity context) {
		showDialog(context, R.string.error, R.string.error_no_internet,
				android.R.drawable.ic_dialog_alert);
	}

	public static void showRetryRequestDialog(Activity activity,
			final AbstractRequest<?> request, final RequestType type,
			DialogInterface.OnClickListener cancelListener) {

		if (activity.isFinishing()) {
			return;
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(R.string.error);
		builder.setMessage(R.string.error_connection);
		builder.setNegativeButton(R.string.cancel, cancelListener);
		builder.setPositiveButton(R.string.retry,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (type) {
						case DELETE:
							request.delete();
							break;
						case GET:
							request.get();
							break;
						case PUT:
							request.put();
							break;
						case POST:
							request.post();
							break;

						default:
							break;
						}

					}
				});

		Dialog d = builder.show();
		FontChanger.setDialogFont(d, activity.getString(R.string.font_default));

	}

	public static void showToast(Activity activity, String text) {

		Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
		FontChanger.setToastFont(toast,
				activity.getString(R.string.font_default));
		toast.show();

	}
}