package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.mvpornek.Model.Kullanıcı.Kullanici;
import com.example.mvpornek.WebService.GetDataService;
import com.example.mvpornek.WebService.RetrofitClientInstance;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterInteractorImpl implements RegisterInteractor {
    GetDataService getDataService;
    @Override
    public void Register(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (TextUtils.isEmpty(adSoyad)) {
                    listener.onAdSoyadHatasi();
                    return;
                }

                if (TextUtils.isEmpty(kullaniciAdi)) {
                    listener.onKullaniciAdiHatasi();
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    listener.onSifreHatasi();
                    return;
                }

                if (TextUtils.isEmpty(sifreTekrar)) {
                    listener.onSifreTekrarHatasi();
                    return;
                }

                if (TextUtils.isEmpty(ePosta)) {
                    listener.onEpostaHatasi();
                    return;
                }

                if (!TextUtils.equals(sifre, sifreTekrar)) {
                    listener.onSifreKontrol();
                    return;
                }
                Call<ResponseBody> call=RetrofitClientInstance
                        .getInstance()
                        .getDataService()
                        .kullaniciKayit(adSoyad,kullaniciAdi,ePosta,sifre,sifreTekrar);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String s=response.body().string();
                            System.out.println("Sunucu Cevabı "+s);
                        }catch (IOException e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println("Bağlantı Hatası "+t.getMessage());
                    }
                });

                listener.onSuccess();
            }
        }, 1000);
    }

}
