package com.example.mvpornek.Activity.Ayarlar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvpornek.R;

public class BildirimlerAnlikBildirimActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirimler_anlik_bildirim);
        Button bildirimIlgiAlaniGeriBtn=findViewById(R.id.bildirimIlgiAlaniGeriBtn);
        bildirimIlgiAlaniGeriBtn.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}