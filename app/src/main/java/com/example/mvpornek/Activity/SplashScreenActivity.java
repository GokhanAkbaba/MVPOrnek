package com.example.mvpornek.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.mvpornek.R;

public class SplashScreenActivity extends Activity {

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
                        Intent intent =new Intent(SplashScreenActivity.this, StartActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent =new Intent(SplashScreenActivity.this, AnaSayfaActivity.class);
                        startActivity(intent);
                    }
                }
                super.run();
            }
        };
        thread.start();
    }
}
