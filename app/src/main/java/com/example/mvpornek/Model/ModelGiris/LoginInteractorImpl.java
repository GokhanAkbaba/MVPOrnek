package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;


public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void Login(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(adSoyad)){
                    listener.onAdSoyadHatasi();
                    return;
                }
                if(TextUtils.isEmpty(kullaniciAdi)){
                    listener.onKullaniciAdiHatasi();
                    return;
                }
               if(TextUtils.isEmpty(sifre)){
                    listener.onSifreHatasi();
                    return;
                }
               if(TextUtils.isEmpty(sifreTekrar)){
                    listener.onSifreTekrarHatasi();
                    return;
                }
               if(TextUtils.isEmpty(ePosta)){
                    listener.onEpostaHatasi();
                    return;
                }
                listener.onSuccess();
            }
        },1000);
    }
}