package com.example.mvpornek.Activity;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvpornek.R;



public class StartActivity extends Activity implements View.OnClickListener {

    Button hesapOlustur;
    TextView girisYapSecenek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_baslangic1);

        hesapOlustur=findViewById(R.id.hesapOlusturBtn);
        hesapOlustur.setOnClickListener(this);

        girisYapSecenek=findViewById(R.id.baslangicGirisSecenek);
        girisYapSecenek.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.hesapOlusturBtn:
                Intent intent= new Intent(StartActivity.this,RegisterActivitiy.class);
                startActivity(intent);
                break;
            case R.id.baslangicGirisSecenek:
                Intent intentLogin= new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intentLogin);
                break;
        }

    }
}
