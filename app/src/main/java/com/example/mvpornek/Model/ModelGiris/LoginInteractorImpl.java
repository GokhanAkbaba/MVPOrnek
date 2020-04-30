package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvpornek.Model.KullaniciGiris.KullaniciGirisResponse;
import com.example.mvpornek.SharedPrefManager;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor{

    Context context;

    public LoginInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void Login(final String ePosta, final String sifre, final onLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(ePosta)){
                    listener.onGirisEPostaHatasi();
                    return;
                }
                if(TextUtils.isEmpty(sifre)){
                    listener.onGirisSifreHatasi();
                    return;
                }
                Call<KullaniciGirisResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .kullaniciGiris(ePosta,sifre);

                call.enqueue(new Callback<KullaniciGirisResponse>() {
                    @Override
                    public void onResponse(Call<KullaniciGirisResponse> call, Response<KullaniciGirisResponse> response) {
                        KullaniciGirisResponse kullaniciGirisResponse=response.body();
                        Boolean hata=response.body().getError();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                                if(hata==true)
                                {
                                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                }else{
                                    SharedPrefManager.getInstance(context)
                                            .kullaniciGiris(kullaniciGirisResponse.getKullaniciGirisModel());
                                    listener.onSuccess();
                                }
                            }
                        }
                    @Override
                    public void onFailure(Call<KullaniciGirisResponse> call, Throwable t) {
                        System.out.println("Bağlantı Hatası "+t.getMessage());
                    }
                });
            }
        },1000);

    }
}
