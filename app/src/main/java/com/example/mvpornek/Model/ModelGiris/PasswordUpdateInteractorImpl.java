package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvpornek.Model.Response.KullaniciResponse;
import com.example.mvpornek.Model.Response.SifreResponse;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordUpdateInteractorImpl implements PasswordUpdateInteractor {
    Context context;

    public PasswordUpdateInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void passwordUpdate(int id, String mevcutSifre, String yeniSifre, String yeniTekrarSifre, onPasswordUpdateFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(mevcutSifre)){
                    listener.onGuncelleMevcutSifreHatasi("Mevcut Şifre Boş Bırakmayınız");
                    return;
                }
                if(TextUtils.isEmpty(yeniSifre)){
                    listener.onGuncelleYeniSifreHatasi();
                    return;
                }
                if(TextUtils.isEmpty(yeniTekrarSifre)){
                    listener.onGuncelleYeniTekrarSifreHatasi();
                    return;
                }
                if (!TextUtils.equals(yeniSifre, yeniTekrarSifre)) {
                    listener.GuncelleYeniSifreKontrolHatasi();
                    return;
                }

                Call<SifreResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .sifreGuncelle(id,mevcutSifre,yeniSifre,yeniTekrarSifre);
                call.enqueue(new Callback<SifreResponse>() {
                    @Override
                    public void onResponse(Call<SifreResponse> call, Response<SifreResponse> response) {
                        SifreResponse sifreResponse=response.body();
                        Boolean hata=response.body().getError();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                            if(hata==false)
                            {
                                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                listener.onSuccess();
                            }else{
                                listener.onGuncelleMevcutSifreHatasi(sifreResponse.getMessage());
                            }
                        }
                        else
                        {
                            Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<SifreResponse> call, Throwable t) {
                        System.out.println("Bağlantı Hatası "+t.getMessage());
                    }
                });
            }
        },1000);
    }
}
