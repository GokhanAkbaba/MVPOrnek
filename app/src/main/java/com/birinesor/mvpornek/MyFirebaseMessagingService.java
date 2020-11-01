package com.birinesor.mvpornek;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private static final String CHANNEL_ID ="birine_sor";
    private static final String CHANNEL_NAME ="Birine_Sor";
    private static final String CHANNEL_DESC ="Birine_Sor_Notifications";
    BildirimFonksiyonları bildirimFonksiyonları=new BildirimFonksiyonları(this);

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                bildirimFonksiyonları.createNotificationChannel();
                bildirimFonksiyonları.displayNotification(json);

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

}

