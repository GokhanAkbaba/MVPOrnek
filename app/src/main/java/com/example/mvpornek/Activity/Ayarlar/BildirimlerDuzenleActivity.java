package com.example.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mvpornek.R;

public class BildirimlerDuzenleActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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