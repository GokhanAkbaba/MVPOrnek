package com.example.mvpornek.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvpornek.R;

public class BaslarkenActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baslarken);

        findViewById(R.id.baslarkenSonrakiBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent= new Intent(this,AnaSayfaActivity.class);
        startActivity(intent);
    }
}
