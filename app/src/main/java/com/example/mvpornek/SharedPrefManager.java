package com.example.mvpornek;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvpornek.Model.Kullanıcı.Kullanici;
import com.example.mvpornek.Model.Kullanıcı.KullaniciResponse;

import retrofit2.Callback;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx)
    {
        this.mCtx=mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if(mInstance == null)
        {
            mInstance = new SharedPrefManager(mCtx);
        }

        return mInstance;
    }


    public void kullaniciKayit(Kullanici kullanici){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt("id",kullanici.getId());
        editor.putString("AdSoyad",kullanici.getAdSoyad());
        editor.putString("KullaniciAdi",kullanici.getKullaniciAdi());
        editor.apply();

    }

    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1) != -1;
    }

    public Kullanici getKullanici()
    {
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new Kullanici(
                sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("adSoyad",null),
                sharedPreferences.getString("KullaniciAdi",null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
