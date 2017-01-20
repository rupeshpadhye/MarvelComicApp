package com.marvel.android.app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class AppUtils {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager conManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();
        return  netInfo != null && netInfo.isConnected();
    }
}
