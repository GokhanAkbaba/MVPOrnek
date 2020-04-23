package com.example.mvpornek.Model.ModelGiris;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

public class RegisterInteractorImpl implements RegisterInteractor {

    @Override
    public void Register(final String kullaniciAdi, final String adSoyad, final String sifre, final String sifreTekrar, final String ePosta, final onRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(adSoyad)){
                    listener.onAdSoyadHatasi();
                    return;
                }

                if(!TextUtils.isEmpty(adSoyad))
                {
                    listener.onOffAdSoyadHatasi();
                    return;
                }

                if(TextUtils.isEmpty(kullaniciAdi)){
                    listener.onKullaniciAdiHatasi();
                    return;
                }
                if(!TextUtils.isEmpty(kullaniciAdi))
                {
                    listener.onOffKullaniciAdiHatasi();
                    return;
                }

                if(TextUtils.isEmpty(sifre)){
                    listener.onSifreHatasi();
                    return;
                }

                if(!TextUtils.isEmpty(sifreTekrar))
                {
                    listener.onOffSifreHatasi();
                    return;
                }

                if(TextUtils.isEmpty(sifreTekrar)){
                    listener.onSifreTekrarHatasi();
                    return;
                }

                if(!TextUtils.isEmpty(sifreTekrar))
                {
                    listener.onOffSifreTekrarHatasi();
                    return;
                }

                if(TextUtils.isEmpty(ePosta)){
                    listener.onEpostaHatasi();
                    return;
                }
                if(!TextUtils.isEmpty(ePosta))
                {
                    listener.onOffEpostaHatasi();
                    return;
                }

                if(!TextUtils.equals(sifre,sifreTekrar))
                {
                    listener.onSifreKontrol();
                    return;
                }

                if(TextUtils.equals(sifre,sifreTekrar))
                {
                    listener.onOffSifreKontrol();
                    return;
                }
                listener.onSuccess();
            }
        },1000);
    }

}
