package com.example.mvpornek.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.mvpornek.R;
import com.example.mvpornek.View.SplashScreenView;

public class SplashScreenActivity extends Activity implements SplashScreenView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_baslangic);

        SharedPreferences prefs=getSharedPreferences("login",MODE_PRIVATE);
        final boolean isLoged=prefs.getBoolean("girisYapildi",false);
        Thread thread= new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(isLoged == false){
                        startActivity();
                    }else{
                        homeActivity();
                    }
                }
                super.run();
            }
        };
        thread.start();
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(getApplicationContext(),StartActivity.class));
    }

    @Override
    public void homeActivity() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}
