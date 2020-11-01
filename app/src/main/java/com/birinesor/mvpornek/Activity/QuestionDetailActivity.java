package com.birinesor.mvpornek.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.birinesor.mvpornek.R;

public class QuestionDetailActivity extends Activity implements View.OnClickListener {
    Button soruAyrintiGeriButon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soru_ayrinti_ekrani);
        soruAyrintiGeriButon=findViewById(R.id.soruAyrintiGeriBtn);
        soruAyrintiGeriButon.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.alerter_slide_in_from_left,R.anim.alerter_slide_out_to_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.soruAyrintiGeriBtn:
                onBackPressed();
                break;
        }
    }
}
