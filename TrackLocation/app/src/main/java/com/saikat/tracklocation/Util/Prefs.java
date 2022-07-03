package com.saikat.tracklocation.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.saikat.tracklocation.model.LatLong;

public class Prefs {
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    private static final String SHARED_PREF_NAME = "userlocationapp";
    private static Prefs preferences;
    private static Context ctx;

    private Prefs(Context context) {
        ctx = context;
    }

    public static synchronized Prefs getInstance(Context context) {
        if (preferences == null) {
            preferences = new Prefs(context);
        }
        return preferences;
    }


    public void userCurrentLocation(LatLong latLong) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LATITUDE, latLong.getUserLagtude());
        editor.putString(LONGITUDE, latLong.getUserLongittude());
    }

    public LatLong getLatLong() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new LatLong(
                sharedPreferences.getString(LATITUDE, null),
                sharedPreferences.getString(LONGITUDE, null)
        );

    }

    public void savedData(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
