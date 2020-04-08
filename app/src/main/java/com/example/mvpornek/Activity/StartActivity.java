package com.example.mvpornek.Activity;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvpornek.R;



public class StartActivity extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_baslangic1);

        findViewById(R.id.hesapOlusturBtn).setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent intent= new Intent(StartActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
