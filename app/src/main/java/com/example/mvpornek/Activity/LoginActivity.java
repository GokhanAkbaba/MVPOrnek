package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mvpornek.R;

public class LoginActivity extends Activity implements View.OnClickListener {
    TextView kayitOlSecenek;
    ImageButton girisYap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_kullanici_giris);
        kayitOlSecenek=findViewById(R.id.kayitOlSecenekTxt);
        kayitOlSecenek.setOnClickListener(this);

        girisYap=findViewById(R.id.girisYapBtn);
        girisYap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.kayitOlSecenekTxt:
                startActivity(new Intent(getApplicationContext(),RegisterActivitiy.class));
                break;
            case R.id.girisYapBtn:
                break;
        }

    }
}
