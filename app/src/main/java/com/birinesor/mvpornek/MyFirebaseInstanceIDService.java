package com.birinesor.mvpornek;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyFirebaseInstanceIDService extends Service {
    private static final String TAG = "MyFirebaseMsgService";
    private static final String CHANNEL_ID ="birine_sor";
    private static final String CHANNEL_NAME ="Birine_Sor";
    private static final String CHANNEL_DESC ="Birine_Sor_Notifications";
    BildirimFonksiyonları bildirimFonksiyonları=new BildirimFonksiyonları(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
