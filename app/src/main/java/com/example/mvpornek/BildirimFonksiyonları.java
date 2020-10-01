package com.example.mvpornek;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.mvpornek.Activity.HomeActivity;
import com.example.mvpornek.Activity.NotificationCommentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class BildirimFonksiyonları {
    private static int count = 0 ;
    Context context;

    public BildirimFonksiyonları(Context context) {
        this.context = context;
    }

    private static final String TAG = "MyFirebaseMsgService";
    private static final String CHANNEL_ID ="birine_sor";
    private static final String CHANNEL_NAME ="Birine_Sor";
    private static final String CHANNEL_DESC ="Birine_Sor_Notifications";
    public void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESC);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void displayNotification(JSONObject json){




        try {
            JSONObject data = json.getJSONObject("data");
            String title = data.getString("hedefID");
            String message = data.getString("message");
            String kullaniciAdi = data.getString("kullaniciAdi");
            String icerikID = data.getString("icerikID");

            Intent resultIntent = new Intent(context, NotificationCommentActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            resultIntent.putExtra("icerikID",icerikID);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(context,0, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(kullaniciAdi)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            int uniqID = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            notificationManager.notify(uniqID, builder.build());
            count++;
            setCount(count);

        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        HomeActivity.getInstance().notificationView(count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
