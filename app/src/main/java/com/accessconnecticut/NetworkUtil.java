package com.accessconnecticut;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

public class NetworkUtil {

	/**
	 * Checks whether Internet connection is available at current moment
	 * @param context The context to use. Usually your {@link Application} or {@link Activity} object.
	 * @return <code>true</code> if Internet is available, <code>false</code> otherwise
	 */
	public static boolean isNetworkAvailable(Context context) {
	    ConnectivityManager cm =
	        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public static void alert(Context context, String message) {
		Toast msg = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
		msg.show();
	}
}
