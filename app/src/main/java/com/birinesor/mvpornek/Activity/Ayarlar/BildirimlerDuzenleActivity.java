package com.birinesor.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.birinesor.mvpornek.InitApplication;
import com.birinesor.mvpornek.R;

public class BildirimlerDuzenleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (InitApplication.getInstance(this).isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setStatusBarColor(getResources().getColor(R.color.whiteStatus));
            if (Build.VERSION.SDK_INT >= 27) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.whiteStatus));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        setContentView(R.layout.activity_bildirimler_duzenle);
        RelativeLayout relativeLayout=findViewById(R.id.layout);
        relativeLayout.setOnClickListener(this);
        Button bildirimDuzenleGeriBtn=findViewById(R.id.bildirimDuzenleGeriBtn);
        bildirimDuzenleGeriBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bildirimDuzenleGeriBtn:
                onBackPressed();
                break;
            case R.id.layout:
                startActivity(new Intent(getApplication().getApplicationContext(), BildirimlerAnlikBildirimActivity.class));
                this.overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
                break;
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }
}