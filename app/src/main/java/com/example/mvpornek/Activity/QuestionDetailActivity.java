package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvpornek.Model.Kullanıcı.SearchModel;
import com.example.mvpornek.R;

import java.util.ArrayList;

public class QuestionDetailActivity extends Activity implements View.OnClickListener {
    Button soruAyrintiGeriButon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soru_ayrinti_ekrani);
        soruAyrintiGeriButon=findViewById(R.id.soruAyrintiGeriBtn);
        soruAyrintiGeriButon.setOnClickListener(this);
        /*Intent intent=getIntent();
        if(intent.getExtras()!= null)
        {
            SearchModel searchModel=(SearchModel) intent.getExtras().getSerializable("data");
            denemeTxt.setText(searchModel.getKullaniciAdi());
        }*/

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
