package com.example.mvpornek.Model.ModelGiris;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvpornek.Model.Kullanıcı.KullaniciKayit.KullaniciResponse;
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
                Call<KullaniciResponse> call= RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .kullaniciGiris(ePosta,sifre);

                call.enqueue(new Callback<KullaniciResponse>() {
                    @Override
                    public void onResponse(Call<KullaniciResponse> call, Response<KullaniciResponse> response) {
                        KullaniciResponse kullaniciGirisResponse=response.body();
                        Boolean hata=response.body().getError();
                        if (response.isSuccessful() && response.body() !=null)
                        {
                                if(hata==false)
                                {
                                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                    SharedPrefManager.getInstance(context)
                                            .kullaniciKayit(kullaniciGirisResponse.getKullanici());
                                    listener.onSuccess();
                                }else{
                                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    @Override
                    public void onFailure(Call<KullaniciResponse> call, Throwable t) {
                        System.out.println("Bağlantı Hatası "+t.getMessage());
                    }
                });
            }
        },1000);

    }
}
