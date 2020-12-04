package com.birinesor.mvpornek;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public
class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private boolean isNightModeEnabled = false;

    private static InitApplication singleton = null;
    private Context mCtx;

    public InitApplication(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static InitApplication getInstance(Context mCtx) {

        if(singleton == null)
        {
            singleton = new InitApplication(mCtx);
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        SharedPreferences geceModu = mCtx.getSharedPreferences("this",Context.MODE_PRIVATE);
        this.isNightModeEnabled = geceModu.getBoolean(NIGHT_MODE, false);
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;

        SharedPreferences mPrefs = mCtx.getSharedPreferences("this",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }
}
